package com.superman.timetask.quartz.config.task;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Component;

/**
 * @author Ningsc
 * @description  定时任务实例
 * @date 16:26 2019/6/19
 */
@Component
public class QuartzJob2 implements Job {

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        System.out.println("定时任务Quartz2222测试！！！！");
    }

}
