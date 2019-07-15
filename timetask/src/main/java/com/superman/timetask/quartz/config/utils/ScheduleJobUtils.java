package com.superman.timetask.quartz.config.utils;


import com.superman.timetask.quartz.domain.ScheduleJob;
import com.superman.timetask.quartz.domain.TaskDO;

/**
 * @author: Ningsc
 * @create: 2019-06-19 17:01
 * @description:
 **/
public class ScheduleJobUtils {

	public static ScheduleJob entityToData(TaskDO scheduleJobEntity) {
		ScheduleJob scheduleJob = new ScheduleJob();
		scheduleJob.setBeanClass(scheduleJobEntity.getBeanClass());
		scheduleJob.setCronExpression(scheduleJobEntity.getCronExpression());
		scheduleJob.setDescription(scheduleJobEntity.getDescription());
		scheduleJob.setIsConcurrent(scheduleJobEntity.getIsConcurrent());
		scheduleJob.setJobName(scheduleJobEntity.getJobName());
		scheduleJob.setJobGroup(scheduleJobEntity.getJobGroup());
		scheduleJob.setJobStatus(scheduleJobEntity.getJobStatus());
		scheduleJob.setMethodName(scheduleJobEntity.getMethodName());
		scheduleJob.setSpringBean(scheduleJobEntity.getSpringBean());
		return scheduleJob;
	}
}