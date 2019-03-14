package com.framgia.config;

import java.time.LocalDateTime;
import java.util.Properties;

import com.framgia.job.PostChatWorkJob;
import com.framgia.service.ChatworkService;
import com.framgia.support.FaceBookClient;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.SimpleTrigger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.quartz.SimpleTriggerFactoryBean;

import com.framgia.job.JobFactory;

@Slf4j
@Configuration
public class QuartzConfig {

    @Value("${org.quartz.scheduler.instanceName}")
    private String instanceName;

    @Value("${org.quartz.scheduler.instanceId}")
    private String instanceId;

    @Value("${org.quartz.threadPool.threadCount}")
    private String threadCount;

    @Value("${job.startDelay}")
    private Long startDelay;

    @Value("${job.repeatInterval}")
    private Long repeatInterval;

    @Value("${job.description}")
    private String description;

    @Value("${job.key}")
    private String key;

    @Bean(name = "caculatePointsTriggerBean")
    public SimpleTriggerFactoryBean caculatePointsTriggerBean() {
        SimpleTriggerFactoryBean factoryBean = new SimpleTriggerFactoryBean();
        factoryBean.setJobDetail(caculateJobDetails().getObject());
        factoryBean.setStartDelay(startDelay);
        factoryBean.setRepeatInterval(repeatInterval);
        factoryBean.setRepeatCount(SimpleTrigger.REPEAT_INDEFINITELY);
        factoryBean.setMisfireInstruction(SimpleTrigger.MISFIRE_INSTRUCTION_RESCHEDULE_NEXT_WITH_REMAINING_COUNT);
        return factoryBean;
    }

    @Bean(name = "cronCaculatePointsTriggerBean")
    public CronTriggerFactoryBean cronCaculatePointsTriggerBean() {
        CronTriggerFactoryBean stFactory = new CronTriggerFactoryBean();
        stFactory.setJobDetail(caculateJobDetails().getObject());
        stFactory.setStartDelay(startDelay);
        //Run 23h every day
        //stFactory.setCronExpression("0 0 23 1/1 * ? *");

        //Run 23h10 every day
        //stFactory.setCronExpression("0 10 23 1/1 * ? *");

        //Run per minus
        stFactory.setCronExpression("0 0/1 * 1/1 * ? *");

        //Run per hour
        //stFactory.setCronExpression("0 0 0/1 1/1 * ? *");
        return stFactory;
    }

    @Bean(name = "caculateJobDetails")
    public JobDetailFactoryBean caculateJobDetails() {
        JobDetailFactoryBean jobDetailFactoryBean = new JobDetailFactoryBean();
        jobDetailFactoryBean.setJobClass(PostChatWorkJob.class);
        jobDetailFactoryBean.setDescription(description);
        jobDetailFactoryBean.setDurability(true);
        jobDetailFactoryBean.setName(key);

        return jobDetailFactoryBean;
    }

    @Autowired
    @Qualifier("cronCaculatePointsTriggerBean")
    private CronTriggerFactoryBean cronCaculatePointsTrigger;

    @Bean
    public org.quartz.spi.JobFactory jobFactory(ApplicationContext applicationContext) {
        JobFactory JobFactory = new JobFactory();
        JobFactory.setApplicationContext(applicationContext);
        return JobFactory;
    }

    @Bean
    public SchedulerFactoryBean schedulerFactoryBean(ApplicationContext applicationContext) {
        SchedulerFactoryBean factory = new SchedulerFactoryBean();

        factory.setOverwriteExistingJobs(true);
        factory.setJobFactory(jobFactory(applicationContext));

        Properties quartzProperties = new Properties();
        quartzProperties.setProperty("org.quartz.scheduler.instanceName", instanceName);
        quartzProperties.setProperty("org.quartz.scheduler.instanceId", instanceId);
        quartzProperties.setProperty("org.quartz.threadPool.threadCount", threadCount);

        factory.setQuartzProperties(quartzProperties);
        factory.setTriggers(cronCaculatePointsTrigger.getObject());

        log.info("starting jobs...");

        return factory;
    }
}
