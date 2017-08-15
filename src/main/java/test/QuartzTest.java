package test;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.Trigger;
import org.quartz.SchedulerException;
import org.quartz.impl.StdSchedulerFactory;
import static org.quartz.JobBuilder.*;
import static org.quartz.TriggerBuilder.*;
import static org.quartz.SimpleScheduleBuilder.*;

public class QuartzTest {

    public static void main(String[] args) {
        try {

            // Grab the Scheduler instance from the Factory
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

            scheduler.start();

            JobDetail jobDetail = newJob(MyJob.class)
                    .withIdentity("job1","group1")
                    .build();

            Trigger trigger = newTrigger()
                    .withIdentity("trigger1","group1")
                    .startNow()
                    .withSchedule(simpleSchedule().withIntervalInSeconds(5).repeatForever())
                    .build();

            Trigger.TriggerState state = scheduler.getTriggerState(trigger.getKey());
            if ("PAUSED".equals(state.name())) {
                scheduler.resumeTrigger(trigger.getKey());
                scheduler.resumeAll();
                scheduler.rescheduleJob(trigger.getKey(), trigger);
            } else {
                scheduler.scheduleJob(jobDetail, trigger);
            }

        } catch (SchedulerException e) {

        }
    }
}
