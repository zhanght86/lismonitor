package test;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyJob implements Job {

    private static final Logger LOGGER = LoggerFactory.getLogger(MyJob.class);

    public MyJob() {

    }

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        LOGGER.info("MyJob is executing...." + System.currentTimeMillis());
//        System.out.println("MyJob is executing...." + System.currentTimeMillis());
    }
}
