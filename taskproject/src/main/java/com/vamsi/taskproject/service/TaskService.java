package com.vamsi.taskproject.service;

import java.util.List;

import com.vamsi.taskproject.payload.TaskDto;

public interface TaskService {

	public TaskDto saveTask(long userId,TaskDto taskDto);
	
	public List<TaskDto> getAllTasks(long userId);
	
	public TaskDto getTask(long userId,long id);
	
	public void deleteTask(long userId,long id);
}
