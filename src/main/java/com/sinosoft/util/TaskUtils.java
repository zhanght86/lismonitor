package com.sinosoft.util;

import com.sinosoft.domain.ScheduleJob;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TaskUtils {

    public final static org.slf4j.Logger LOGGER = LoggerFactory.getLogger(TaskUtils.class);

    public static void invokMethod(ScheduleJob scheduleJob) {
        LOGGER.info("START!任务名称 = [" + scheduleJob.getJobName() + "]----------运行开始");
        Object object = null;
        Class clazz;
        if (StringUtils.isNotBlank(scheduleJob.getSpringId())) {
            object = SpringUtils.getBean(scheduleJob.getSpringId());
        } else if (StringUtils.isNotBlank(scheduleJob.getBeanClass())) {
            try {
                clazz = Class.forName(scheduleJob.getBeanClass());
                object = clazz.newInstance();
            } catch (Exception e) {
                LOGGER.error("获取Bean失败", e);
            }

        }
        if (object == null) {
            LOGGER.error("任务名称 = [" + scheduleJob.getJobName() + "]---------------未启动成功，请检查是否配置正确！！！");
            return;
        }
        clazz = object.getClass();
        Method method = null;
        try {
            method = clazz.getDeclaredMethod(scheduleJob.getMethodName());
        } catch (NoSuchMethodException | SecurityException e) {
            LOGGER.error("任务名称 = [" + scheduleJob.getJobName() + "]---------------未启动成功，方法名设置错误！！！");
        }
        if (method != null) {
            try {
                method.invoke(object);
            } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                LOGGER.error("任务名称 = [" + scheduleJob.getJobName() + "]----------启动异常", e);
            }
        }
        LOGGER.info("SUCCESS!任务名称 = [" + scheduleJob.getJobName() + "]----------运行结束");
    }
}
