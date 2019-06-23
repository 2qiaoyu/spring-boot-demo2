package com.joham.demo.config;

import com.joham.demo.quartz.TestQuartz;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author joham
 */
@Configuration
public class QuartzConfig {

    @Bean
    public JobDetail teatQuartzDetail() {
        return JobBuilder.newJob(TestQuartz.class).withIdentity("testQuartz").storeDurably().build();
    }

    @Bean
    public Trigger testQuartzTrigger() {
        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
                //设置时间周期单位秒
                .withIntervalInSeconds(10000)
                .repeatForever();
        return TriggerBuilder.newTrigger().forJob(teatQuartzDetail())
                .withIdentity("testQuartz")
                .withSchedule(scheduleBuilder)
                .build();
    }
}
