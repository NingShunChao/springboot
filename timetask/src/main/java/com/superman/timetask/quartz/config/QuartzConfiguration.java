package com.superman.timetask.quartz.config;

import com.superman.timetask.quartz.config.factory.QuartzJobFactory;
import com.superman.timetask.quartz.domain.ScheduleJob;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.spi.JobFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import java.io.IOException;
import java.util.Properties;

/**
 * @author: Ningsc
 * @create: 2019-06-19 22:34
 * @description: 定时配置（可以配置静态定时任务）
 **/
@Configuration
public class QuartzConfiguration {

    @Autowired
    QuartzJobFactory quartzJobFactory;

    /**
    * @Author: Ningsc
    * @Date: 2019/6/19
    * @Description:  生成调度实例的工厂类
    * @Param:
    * @return:
    */
    @Bean
    public SchedulerFactoryBean schedulerFactoryBean() {
        SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
        try {
            schedulerFactoryBean.setOverwriteExistingJobs(true);
            schedulerFactoryBean.setQuartzProperties(quartzProperties());
            schedulerFactoryBean.setJobFactory(quartzJobFactory);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return schedulerFactoryBean;
    }


    /**
    * @Author: Ningsc
    * @Date: 2019/6/19
    * @Description:  【属性基本配置参数】比如配置调度线程数，调度线程池类型等
    * @Param:
    * @return:
    */
    @Bean
    public Properties quartzProperties() throws IOException {
        PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
        propertiesFactoryBean.setLocation(new ClassPathResource("/config/quartz.properties"));
        propertiesFactoryBean.afterPropertiesSet();
        return propertiesFactoryBean.getObject();
    }

    /**
     * 创建schedule
     * @return
     */
    @Bean(name = "scheduler")
    public Scheduler scheduler() {
        return schedulerFactoryBean().getScheduler();
    }




}
