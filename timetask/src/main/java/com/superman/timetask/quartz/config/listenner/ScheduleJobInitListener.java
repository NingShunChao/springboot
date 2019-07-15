package com.superman.timetask.quartz.config.listenner;

import com.superman.timetask.quartz.servcie.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author Ningsc
 * @description  定时任务初始化加载监听
 * @date 16:26 2019/6/19
 */
@Order(value = 1)
@Component
public class ScheduleJobInitListener implements CommandLineRunner {

    @Autowired
    JobService scheduleJobService;

    @Override
    public void run(String... args) throws Exception {
        try {
            scheduleJobService.initSchedule();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
