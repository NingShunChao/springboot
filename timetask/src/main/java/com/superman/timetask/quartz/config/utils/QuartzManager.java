package com.superman.timetask.quartz.config.utils;

import com.superman.timetask.quartz.domain.ScheduleJob;
import org.quartz.*;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author Ningsc
 * @description  定时任务管理
 * @date 16:26 2019/6/19
 */
@Service
public class QuartzManager {
   /* public final Logger log = LoggerFactory.getLogger(QuartzManager.class);*/

    /**
     * 任务调度类
     */
    @Autowired
    private Scheduler scheduler;

   /**
   * @Author: Ningsc
   * @Date: 2019/6/19
   * @Description:   添加定时任务
   * @Param:
   * @return:
   */
    public void addJob(ScheduleJob job) {
        try {
            // 创建jobDetail实例，绑定Job实现类
            // 指明job的名称，所在组的名称，以及绑定job类
            Class<? extends Job> jobClass = (Class<? extends Job>) (Class.forName(job.getBeanClass()).newInstance()
                    .getClass());
            // 任务名称和组构成任务key
            JobDetail jobDetail = JobBuilder.newJob(jobClass).withIdentity(job.getJobName(), job.getJobGroup())
                    .build();
            // 定义调度触发规则
            // 使用cornTrigger规则
            // 触发器key
            Trigger trigger = TriggerBuilder.newTrigger().withIdentity(job.getJobName(), job.getJobGroup())
                    .startAt(DateBuilder.futureDate(1, DateBuilder.IntervalUnit.SECOND))
                    .withSchedule(CronScheduleBuilder.cronSchedule(job.getCronExpression())).startNow().build();
            // 把作业和触发器注册到任务调度中
            scheduler.scheduleJob(jobDetail, trigger);
            // 启动
            if (!scheduler.isShutdown()) {
                scheduler.start();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    /**
    * @Author: Ningsc
    * @Date: 2019/6/19
    * @Description:   获取所有计划中的任务列表
    * @Param:
    * @return:
    */
    public List<ScheduleJob> getAllJob() throws SchedulerException {
        GroupMatcher<JobKey> matcher = GroupMatcher.anyJobGroup();
        Set<JobKey> jobKeys = scheduler.getJobKeys(matcher);
        List<ScheduleJob> jobList = new ArrayList<ScheduleJob>();
        for (JobKey jobKey : jobKeys) {
            List<? extends Trigger> triggers = scheduler.getTriggersOfJob(jobKey);
            for (Trigger trigger : triggers) {
                ScheduleJob job = new ScheduleJob();
                job.setJobName(jobKey.getName());
                job.setJobGroup(jobKey.getGroup());
                job.setDescription("触发器:" + trigger.getKey());
                Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());
                job.setJobStatus(triggerState.name());
                if (trigger instanceof CronTrigger) {
                    CronTrigger cronTrigger = (CronTrigger) trigger;
                    String cronExpression = cronTrigger.getCronExpression();
                    job.setCronExpression(cronExpression);
                }
                jobList.add(job);
            }
        }
        return jobList;
    }


    /**
    * @Author: Ningsc
    * @Date: 2019/6/19
    * @Description:   所有正在运行的job
    * @Param:
    * @return:
    */
    public List<ScheduleJob> getRunningJob() throws SchedulerException {
        List<JobExecutionContext> executingJobs = scheduler.getCurrentlyExecutingJobs();
        List<ScheduleJob> jobList = new ArrayList<ScheduleJob>(executingJobs.size());
        for (JobExecutionContext executingJob : executingJobs) {
            ScheduleJob job = new ScheduleJob();
            JobDetail jobDetail = executingJob.getJobDetail();
            JobKey jobKey = jobDetail.getKey();
            Trigger trigger = executingJob.getTrigger();
            job.setJobName(jobKey.getName());
            job.setJobGroup(jobKey.getGroup());
            job.setDescription("触发器:" + trigger.getKey());
            Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());
            job.setJobStatus(triggerState.name());
            if (trigger instanceof CronTrigger) {
                CronTrigger cronTrigger = (CronTrigger) trigger;
                String cronExpression = cronTrigger.getCronExpression();
                job.setCronExpression(cronExpression);
            }
            jobList.add(job);
        }
        return jobList;
    }


    /**
    * @Author: Ningsc
    * @Date: 2019/6/19
    * @Description:   暂停一个job
    * @Param:
    * @return:
    */
    public void pauseJob(ScheduleJob scheduleJob) throws SchedulerException {
        JobKey jobKey = JobKey.jobKey(scheduleJob.getJobName(), scheduleJob.getJobGroup());
        scheduler.pauseJob(jobKey);
    }

   /**
   * @Author: Ningsc
   * @Date: 2019/6/19
   * @Description:   删除一个job
   * @Param:
   * @return:
   */
    public void deleteJob(ScheduleJob scheduleJob) throws SchedulerException {
        JobKey jobKey = JobKey.jobKey(scheduleJob.getJobName(), scheduleJob.getJobGroup());
        scheduler.deleteJob(jobKey);

    }


    /**
    * @Author: Ningsc
    * @Date: 2019/6/19
    * @Description:  恢复一个job
    * @Param:
    * @return:
    */
    public void resumeJob(ScheduleJob scheduleJob) throws SchedulerException {
        JobKey jobKey = JobKey.jobKey(scheduleJob.getJobName(), scheduleJob.getJobGroup());
        scheduler.resumeJob(jobKey);
    }

    /**
    * @Author: Ningsc
    * @Date: 2019/6/19
    * @Description:  立即执行job
    * @Param:
    * @return:
    */
    public void runJobNow(ScheduleJob scheduleJob) throws SchedulerException {
        JobKey jobKey = JobKey.jobKey(scheduleJob.getJobName(), scheduleJob.getJobGroup());
        scheduler.triggerJob(jobKey);
    }


    /**
    * @Author: Ningsc
    * @Date: 2019/6/19
    * @Description:  更新job时间表达式
    * @Param:
    * @return:
    */
    public void updateJobCron(ScheduleJob scheduleJob) throws SchedulerException {

        TriggerKey triggerKey = TriggerKey.triggerKey(scheduleJob.getJobName(), scheduleJob.getJobGroup());

        CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);

        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(scheduleJob.getCronExpression());

        trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();

        scheduler.rescheduleJob(triggerKey, trigger);
    }



}
