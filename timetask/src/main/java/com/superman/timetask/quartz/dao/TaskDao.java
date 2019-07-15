package com.superman.timetask.quartz.dao;

import com.superman.timetask.quartz.domain.TaskDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;


/**
 * @author: Ningsc
 * @create: 2019-06-19 17:00
 * @description:
 **/
@Mapper
public interface TaskDao {

	TaskDO get(Long id);
	
	List<TaskDO> list(Map<String, Object> map);

	int count(Map<String, Object> map);
	
	int save(TaskDO task);
	
	int update(TaskDO task);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);


}
