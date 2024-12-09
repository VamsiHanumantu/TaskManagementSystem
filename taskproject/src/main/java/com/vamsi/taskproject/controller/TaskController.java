package com.vamsi.taskproject.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vamsi.taskproject.payload.TaskDto;
import com.vamsi.taskproject.service.TaskService;

@RestController
@RequestMapping("/api")
public class TaskController {
    
	@Autowired
	private TaskService taskService;
	//save the task
	@PostMapping("/{userid}/tasks")
	public ResponseEntity<TaskDto> saveTask(@PathVariable(  "userid") long userid, @RequestBody TaskDto taskDto){
		
		return new ResponseEntity<TaskDto>(taskService.saveTask(userid, taskDto),HttpStatus.CREATED);
	}
	
	//get all tasks
	@GetMapping("/{userId}/tasks")
	public ResponseEntity<List<TaskDto>> getAllTasks(@PathVariable(name="userId") long userId){
		
		return new ResponseEntity<List<TaskDto>>(taskService.getAllTasks(userId),HttpStatus.OK);
	}
	
	//get individual task
	@GetMapping("/{userId}/tasks/{id}")
	public ResponseEntity<TaskDto> getTask(@PathVariable(name="userId") long userId, @PathVariable(name="id") long id){
		
		return new ResponseEntity<TaskDto>(taskService.getTask(userId, id),HttpStatus.OK);
	}
	
	//delete individual task
	@DeleteMapping("/{userId}/tasks/{id}")
	public ResponseEntity<String> deleteTask(@PathVariable(name="userId") long userId, @PathVariable(name="id") long id){
		
		taskService.deleteTask(userId, id);
		return new ResponseEntity<String>("User Deleted Successfully",HttpStatus.OK);
	}
	
}
