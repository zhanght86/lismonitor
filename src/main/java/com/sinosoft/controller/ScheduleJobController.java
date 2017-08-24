package com.sinosoft.controller;

import com.alibaba.fastjson.JSON;
import com.sinosoft.domain.ScheduleJob;
import com.sinosoft.service.JobTaskService;
import com.sinosoft.support.ResultObj;
import com.sinosoft.util.SpringUtils;
import org.apache.commons.lang3.StringUtils;
import org.quartz.CronScheduleBuilder;
import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.List;

@Controller
@RequestMapping("/task")
public class ScheduleJobController {

    private final static Logger LOGGER = LoggerFactory.getLogger(ScheduleJobController.class);


    private JobTaskService jobTaskService;

    private ResultObj resultObj;

    @Autowired
    public void setJobTaskService(JobTaskService jobTaskService) {
        this.jobTaskService = jobTaskService;
    }

    @Autowired
    public void setResultObj(ResultObj resultObj) {
        this.resultObj = resultObj;
    }
    /**
     * 新增一个计划
     * */
    @RequestMapping("add")
    @ResponseBody
    public ResultObj addTask(HttpServletRequest request, ScheduleJob scheduleJob) {
        resultObj.setFlag(false);

        //校验正则表达式是否正确
        try {
            CronScheduleBuilder.cronSchedule(scheduleJob.getCronExpression());
        } catch (Exception e) {
            resultObj.setMsg("cron表达式有误，不能被解析！");
            return resultObj;
        }

        //校验运行的类及方法是否存在
        Object obj;
        try {
            if (StringUtils.isNotBlank(scheduleJob.getSpringId())) {
                obj = SpringUtils.getBean(scheduleJob.getSpringId());
            } else {
                Class clazz = Class.forName(scheduleJob.getBeanClass());
                obj = clazz.newInstance();
            }
        } catch (Exception e) {
            LOGGER.error("获取计划下的处理类异常！", e);
            resultObj.setMsg("获取计划下的处理类异常！");
            return resultObj;
        }
        if (obj == null) {
            resultObj.setMsg("未找到目标类！");
            return resultObj;
        } else {
            Class clazz = obj.getClass();
            Method method;
            try {
                method = clazz.getMethod(scheduleJob.getMethodName(), null);
            } catch (Exception e) {
                LOGGER.error("获取处理类的方法异常！", e);
                resultObj.setMsg("获取处理类的方法异常！");
                return resultObj;
            }
            if (method == null) {
                resultObj.setMsg("未找到目标方法！");
                return resultObj;
            }
        }

        //保存新增的任务
        try {
            jobTaskService.addTask(scheduleJob);
        } catch (Exception e) {
            LOGGER.error("保存失败，检查 name group 组合是否有重复！", e);
            resultObj.setFlag(false);
            resultObj.setMsg("保存失败，检查 name group 组合是否有重复！");
            return resultObj;
        }

        resultObj.setFlag(true);
        return resultObj;
    }

    /**
     * 更改计划状态
     * */
    @RequestMapping("changeJobStatus")
    @ResponseBody
    public ResultObj changeJobStatus(HttpServletRequest request,  Long jobId, String cmd) {
        resultObj.setFlag(false);
        try {
            jobTaskService.changeStatus(jobId, cmd);
        } catch (SchedulerException e) {
            LOGGER.error("任务状态改变失败！", e);
            resultObj.setMsg("任务状态改变失败！");
            return resultObj;
        }
        resultObj.setFlag(true);
        return resultObj;
    }

    /**
     * 更改表达式
     * */
    @RequestMapping("updateCron")
    @ResponseBody
    public ResultObj updateCron(HttpServletRequest request,  Long jobId, String cron) {
        resultObj.setFlag(false);
        try {
            CronScheduleBuilder.cronSchedule(cron);
        } catch (Exception e) {
            resultObj.setMsg("cron表达式有误，不能被解析！");
            return resultObj;
        }
        try {
            jobTaskService.updateCron(jobId, cron);
        } catch (SchedulerException e) {
            resultObj.setMsg("cron更新失败！");
            return resultObj;
        }
        resultObj.setFlag(true);
        return resultObj;
    }

    @RequestMapping("taskList")
    public String taskList(HttpServletRequest request) {
        List<ScheduleJob> taskList = jobTaskService.getAllTask();
        LOGGER.info("进入taskList：" + JSON.toJSONString(taskList));
        request.setAttribute("taskList", taskList);
        return "ui/pages/task";
    }

    @RequestMapping("showList")
    @ResponseBody
    public List<ScheduleJob> showList(HttpServletRequest request) {
        List<ScheduleJob> taskList = jobTaskService.getAllTask();
        LOGGER.info("进入taskList：" + JSON.toJSONString(taskList));
//        request.setAttribute("taskList", taskList);
        return taskList;
    }

}
