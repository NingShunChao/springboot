package com.superman.timetask.quartz.servcie;

import com.superman.timetask.quartz.domain.TaskDO;
import org.quartz.SchedulerException;

import java.util.List;
import java.util.Map;

/**
 * @author: Ningsc
 * @create: 2019-06-19 17:00
 * @description:
 **/
public interface JobService {

    /**
    * @Author: Ningsc
    * @Date: 2019/6/19
    * @Description:  根据Id获取任务
    * @Param:
    * @return:
    */
    TaskDO get(Long id);

    /**
    * @Author: Ningsc
    * @Date: 2019/6/19
    * @Description:
    * @Param:
    * @return:
    */
    List<TaskDO> list(Map<String, Object> map);

    int count(Map<String, Object> map);

    int save(TaskDO taskScheduleJob);

    int update(TaskDO taskScheduleJob);

    int remove(Long id);

    int batchRemove(Long[] ids);

    void initSchedule() throws SchedulerException;

    void changeStatus(Long jobId, String cmd) throws SchedulerException;

    void updateCron(Long jobId) throws SchedulerException;

}
