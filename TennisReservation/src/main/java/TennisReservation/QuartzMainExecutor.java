package TennisReservation;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

public class QuartzMainExecutor {
    
    public static void main(String[] args) throws SchedulerException{



        JobDetail job = JobBuilder.newJob(App.class).build();
        //Trigger t1 = TriggerBuilder.newTrigger().withIdentity("SimpleTrigger").startNow().build();
        Trigger t1 = TriggerBuilder.newTrigger().withIdentity("CronTrigger").withSchedule(CronScheduleBuilder.cronSchedule("0 0/5 * 1/1 * ? *")).build();
        
        Scheduler sc = StdSchedulerFactory.getDefaultScheduler();

        sc.start();
        sc.scheduleJob(job, t1);

    }
}