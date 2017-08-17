package com.sinosoft.job.factory;

import com.sinosoft.domain.ScheduleJob;
import com.sinosoft.util.TaskUtils;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 异步任务
 * */
public class ASyncQuartzJobFactory implements Job {

    public static final Logger LOGGER = LoggerFactory.getLogger(ASyncQuartzJobFactory.class);

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        ScheduleJob scheduleJob = (ScheduleJob) jobExecutionContext.getMergedJobDataMap().get("scheduleJob");
        TaskUtils.invokMethod(scheduleJob);
    }

}
