package com.superman.timetask.quartz.servcie.impl;

import com.superman.timetask.quartz.config.utils.Constant;
import com.superman.timetask.quartz.config.utils.QuartzManager;
import com.superman.timetask.quartz.config.utils.ScheduleJobUtils;
import com.superman.timetask.quartz.dao.TaskDao;
import com.superman.timetask.quartz.domain.ScheduleJob;
import com.superman.timetask.quartz.domain.TaskDO;
import com.superman.timetask.quartz.servcie.JobService;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: Ningsc
 * @create: 2019-06-19 17:01
 * @description:
 **/
@Service
public class JobServiceImpl implements JobService {

    @Autowired
    private TaskDao taskDao;

    @Autowired
    QuartzManager quartzManager;


    @Override
    public TaskDO get(Long id) {
        return taskDao.get(id);
    }

    @Override
    public List<TaskDO> list(Map<String, Object> map) {
        return taskDao.list(map);
    }

    @Override
    public int count(Map<String, Object> map) {
        return taskDao.count(map);
    }

    @Override
    public int save(TaskDO taskScheduleJob) {
        return taskDao.save(taskScheduleJob);
    }

    @Override
    public int update(TaskDO taskScheduleJob) {
        return taskDao.update(taskScheduleJob);
    }

    @Override
    public int remove(Long id) {
        try {
            TaskDO taskScheduleJob = get(id);
            quartzManager.deleteJob(ScheduleJobUtils.entityToData(taskScheduleJob));
            return taskDao.remove(id);
        } catch (SchedulerException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int batchRemove(Long[] ids) {
        for (Long id : ids) {
            try {
                TaskDO taskScheduleJob = get(id);
                quartzManager.deleteJob(ScheduleJobUtils.entityToData(taskScheduleJob));
            } catch (SchedulerException e) {
                e.printStackTrace();
                return 0;
            }
        }
        return taskDao.batchRemove(ids);
    }

    @Override
    public void initSchedule() throws SchedulerException {
        // 这里获取任务信息数据
        List<TaskDO> jobList = taskDao.list(new HashMap<String, Object>(16));
        for (TaskDO taskScheduleJob : jobList) {
            if ("1".equals(taskScheduleJob.getJobStatus())) {
                ScheduleJob job = ScheduleJobUtils.entityToData(taskScheduleJob);
                quartzManager.addJob(job);
            }
        }

    }

    @Override
    public void changeStatus(Long jobId, String cmd) throws SchedulerException {
        TaskDO taskScheduleJob = get(jobId);
        if (taskScheduleJob == null) {
            return;
        }
        if (Constant.STATUS_RUNNING_STOP.equals(cmd)) {
            taskScheduleJob.setJobStatus(ScheduleJob.STATUS_NOT_RUNNING);
            quartzManager.deleteJob(ScheduleJobUtils.entityToData(taskScheduleJob));
        } else if(Constant.STATUS_RUNNING_START.equals(cmd)) {
            taskScheduleJob.setJobStatus(ScheduleJob.STATUS_RUNNING);
            quartzManager.addJob(ScheduleJobUtils.entityToData(taskScheduleJob));
        }else {

        }
        update(taskScheduleJob);

    }

    @Override
    public void updateCron(Long jobId) throws SchedulerException {
        TaskDO taskScheduleJob = get(jobId);
        if (taskScheduleJob == null) {
            return;
        }
        if (ScheduleJob.STATUS_RUNNING.equals(taskScheduleJob.getJobStatus())) {
            quartzManager.updateJobCron(ScheduleJobUtils.entityToData(taskScheduleJob));
        }
        update(taskScheduleJob);
    }
}
