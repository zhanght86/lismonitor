package com.sinosoft.test;

import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyJob implements Job {

    private static final Logger LOGGER = LoggerFactory.getLogger(MyJob.class);

    public MyJob() {

    }

    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        JobKey jobKey = jobExecutionContext.getJobDetail().getKey();
        LOGGER.info("JobKey-name:" + jobKey.getName() + ";JobKey-group:" + jobKey.getGroup() + " is running at:" + System.currentTimeMillis());
//        System.out.println("JobKey-name:" + jobKey.getName() + ";JobKey-group:" + jobKey.getGroup() + " is running at:" + System.currentTimeMillis());
    }
}
