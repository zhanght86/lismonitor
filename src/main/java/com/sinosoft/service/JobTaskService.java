package com.sinosoft.service;

import com.sinosoft.dao.ScheduleJobMapper;
import com.sinosoft.domain.ScheduleJob;
import com.sinosoft.job.factory.ASyncQuartzJobFactory;
import com.sinosoft.job.factory.SyncQuartzJobFactory;
import org.quartz.*;
import org.quartz.impl.matchers.GroupMatcher;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Service
public class JobTaskService {
    public final static org.slf4j.Logger LOGGER = LoggerFactory.getLogger(JobTaskService.class);

    private SchedulerFactoryBean schedulerFactoryBean;

    private ScheduleJobMapper scheduleJobMapper;

    @Autowired
    public void setSchedulerFactoryBean(SchedulerFactoryBean schedulerFactoryBean) {
        this.schedulerFactoryBean = schedulerFactoryBean;
    }

    @Autowired
    public void setScheduleJobMapper(ScheduleJobMapper scheduleJobMapper) {
        this.scheduleJobMapper = scheduleJobMapper;
    }

    public List<ScheduleJob> getAllTask() {
        return scheduleJobMapper.getAll();
    }

    public void addTask(ScheduleJob scheduleJob) {
        scheduleJob.setCreateTime(new Date());
        scheduleJobMapper.insertSelective(scheduleJob);
    }

    public ScheduleJob getTaskById(Long jobId) {
        return scheduleJobMapper.selectByPrimaryKey(jobId);
    }

    public void changeStatus(Long jobId, String cmd) throws SchedulerException {
        ScheduleJob job = getTaskById(jobId);
        if (job == null) {
            return;
        }
        if ("stop".equals(cmd)) {
            deleteJob(job);
            job.setJobStatus(ScheduleJob.STATUS_NOT_RUNNING);
        } else if ("start".equals(cmd)) {
            job.setJobStatus(ScheduleJob.STATUS_RUNNING);
            addJob(job);
        }
        scheduleJobMapper.updateByPrimaryKeySelective(job);
    }

    public void updateCron(Long jobId, String cron) throws SchedulerException {
        ScheduleJob job = getTaskById(jobId);
        if (job == null) {
            return;
        }
        job.setCronExpression(cron);
        if (ScheduleJob.STATUS_RUNNING.equals(job.getJobStatus())) {
            updateJobCron(job);
        }
        scheduleJobMapper.updateByPrimaryKeySelective(job);

    }

    public void addJob(ScheduleJob job) throws SchedulerException {
        if (job == null || !ScheduleJob.STATUS_RUNNING.equals(job.getJobStatus())) {
            return;
        }

        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        LOGGER.debug(scheduler + ".......................................................................................add");
        TriggerKey triggerKey = TriggerKey.triggerKey(job.getJobName(), job.getJobGroup());

        CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);

        // 不存在，创建一个
        if (null == trigger) {
            Class clazz = ScheduleJob.CONCURRENT_IS.equals(job.getIsConcurrent()) ? ASyncQuartzJobFactory.class : SyncQuartzJobFactory.class;

            JobDetail jobDetail = JobBuilder.newJob(clazz).withIdentity(job.getJobName(), job.getJobGroup()).build();

            jobDetail.getJobDataMap().put("scheduleJob", job);

            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(job.getCronExpression());

            trigger = TriggerBuilder.newTrigger().withIdentity(job.getJobName(), job.getJobGroup()).withSchedule(scheduleBuilder).build();

            scheduler.scheduleJob(jobDetail, trigger);
        } else {
            // Trigger已存在，那么更新相应的定时设置
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(job.getCronExpression());

            // 按新的cronExpression表达式重新构建trigger
            trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();

            // 按新的trigger重新设置job执行
            scheduler.rescheduleJob(triggerKey, trigger);
        }
    }

    @PostConstruct
    public void init() throws Exception {

        // 这里获取任务信息数据
        List<ScheduleJob> jobList = scheduleJobMapper.getAll();

        for (ScheduleJob job : jobList) {
            addJob(job);
        }
    }

    public List<ScheduleJob> getAllJob() throws SchedulerException {
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        GroupMatcher<JobKey> matcher = GroupMatcher.anyJobGroup();
        Set<JobKey> jobKeys = scheduler.getJobKeys(matcher);
        List<ScheduleJob> jobList = new ArrayList<>();
        for (JobKey jobKey : jobKeys) {
            List<? extends Trigger> triggers = scheduler.getTriggersOfJob(jobKey);
            for (Trigger trigger : triggers) {
                ScheduleJob job = new ScheduleJob();
                job.setJobName(jobKey.getName());
                job.setJobGroup(jobKey.getGroup());
                job.setDescription("触发器:" + trigger.getKey());
                Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());
                job.setJobStatus(triggerState.name());
                if (trigger instanceof CronTrigger) {
                    CronTrigger cronTrigger = (CronTrigger) trigger;
                    String cronExpression = cronTrigger.getCronExpression();
                    job.setCronExpression(cronExpression);
                }
                jobList.add(job);
            }
        }
        return jobList;
    }

    public List<ScheduleJob> getRunningJob() throws SchedulerException {
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        List<JobExecutionContext> executingJobs = scheduler.getCurrentlyExecutingJobs();
        List<ScheduleJob> jobList = new ArrayList<>(executingJobs.size());
        for (JobExecutionContext executingJob : executingJobs) {
            ScheduleJob job = new ScheduleJob();
            JobDetail jobDetail = executingJob.getJobDetail();
            JobKey jobKey = jobDetail.getKey();
            Trigger trigger = executingJob.getTrigger();
            job.setJobName(jobKey.getName());
            job.setJobGroup(jobKey.getGroup());
            job.setDescription("触发器:" + trigger.getKey());
            Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());
            job.setJobStatus(triggerState.name());
            if (trigger instanceof CronTrigger) {
                CronTrigger cronTrigger = (CronTrigger) trigger;
                String cronExpression = cronTrigger.getCronExpression();
                job.setCronExpression(cronExpression);
            }
            jobList.add(job);
        }
        return jobList;
    }

    public void pauseJob(ScheduleJob scheduleJob) throws SchedulerException {
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        JobKey jobKey = JobKey.jobKey(scheduleJob.getJobName(), scheduleJob.getJobGroup());
        scheduler.pauseJob(jobKey);
    }

    public void resumeJob(ScheduleJob scheduleJob) throws SchedulerException {
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        JobKey jobKey = JobKey.jobKey(scheduleJob.getJobName(), scheduleJob.getJobGroup());
        scheduler.resumeJob(jobKey);
    }

    public void deleteJob(ScheduleJob scheduleJob) throws SchedulerException {
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        JobKey jobKey = JobKey.jobKey(scheduleJob.getJobName(), scheduleJob.getJobGroup());
        scheduler.deleteJob(jobKey);
    }

    public void runAJobNow(ScheduleJob scheduleJob) throws SchedulerException {
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        JobKey jobKey = JobKey.jobKey(scheduleJob.getJobName(), scheduleJob.getJobGroup());
        scheduler.triggerJob(jobKey);
    }

    public void updateJobCron(ScheduleJob scheduleJob) throws SchedulerException {
        Scheduler scheduler = schedulerFactoryBean.getScheduler();

        TriggerKey triggerKey = TriggerKey.triggerKey(scheduleJob.getJobName(), scheduleJob.getJobGroup());

        CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);

        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(scheduleJob.getCronExpression());

        trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();

        scheduler.rescheduleJob(triggerKey, trigger);
    }
}
