package com.sinosoft.controller;

import com.alibaba.fastjson.JSON;
import com.sinosoft.domain.ScheduleJob;
import com.sinosoft.service.JobTaskService;
import com.sinosoft.support.ResultObj;
import com.sinosoft.util.SpringUtils;
import org.apache.commons.lang3.StringUtils;
import org.quartz.CronScheduleBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.List;

@Controller
@RequestMapping("/tasks")
public class TaskController {
    private static Logger LOGGER = LoggerFactory.getLogger(TaskController.class);

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

    //查询所有任务GET(含状态)
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<ScheduleJob> getTasks(HttpServletRequest request) {
        List<ScheduleJob> taskList = null;
        try {
            String state = request.getParameter("state");

            if("1".equals(state)) {
                taskList = jobTaskService.getRunningJob();
            } else {
                taskList = jobTaskService.getAllTask();
            }
        } catch (Exception e) {
            LOGGER.error("获取任务异常！", e);
        }
        return taskList;
    }

    //查询某条任务
    @RequestMapping(value="/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ScheduleJob getOneTask(@PathVariable Long id, HttpServletRequest request) {
        return jobTaskService.getTaskById(id);
    }

    //新增一条任务

    @RequestMapping(value="/new", method = RequestMethod.POST)
    @ResponseBody
    public ResultObj _new(String scheduleJobJSON, HttpServletRequest request) {
        ScheduleJob scheduleJob = (ScheduleJob) JSON.parse(scheduleJobJSON);
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


}
