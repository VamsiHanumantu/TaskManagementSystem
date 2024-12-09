package com.vamsi.taskproject.serviceimpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vamsi.taskproject.exception.APIException;
import com.vamsi.taskproject.exception.TaskNotFound;
import com.vamsi.taskproject.exception.UserNotFound;
import com.vamsi.taskproject.model.Task;
import com.vamsi.taskproject.model.User;
import com.vamsi.taskproject.payload.TaskDto;
import com.vamsi.taskproject.repository.TaskRepository;
import com.vamsi.taskproject.repository.UserRepository;
import com.vamsi.taskproject.service.TaskService;

@Service
public class TaskServiceImpl implements TaskService {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private TaskRepository taskRepository;

	@Override
	public TaskDto saveTask(long userId, TaskDto taskDto) {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new UserNotFound(String.format("User Id %d not found", userId)));
		Task task = modelMapper.map(taskDto, Task.class);
		task.setUser(user);
		// after setting the user, we are saving the data
		Task savedTask = taskRepository.save(task);
		return modelMapper.map(savedTask, TaskDto.class);
	}

	@Override
	public List<TaskDto> getAllTasks(long userId) {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new UserNotFound(String.format("User Id %d not found", userId)));
		List<Task> tasks = taskRepository.findAllByUserId(userId);

		return tasks.stream().map(task -> modelMapper.map(task, TaskDto.class)).collect(Collectors.toList());
	}

	@Override
	public TaskDto getTask(long userId, long id) {
		// TODO Auto-generated method stub
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new UserNotFound(String.format("User Id %d not found", userId)));
		Task task = taskRepository.findById(id)
				.orElseThrow(() -> new TaskNotFound(String.format("Task Id %d not found", id)));
		
		if(user.getId()!= task.getUser().getId()) {
			throw new APIException(String.format("Task Id %d not belong to User Id %d", id,userId));
		}
		
		return modelMapper.map(task, TaskDto.class);
	}

	@Override
	public void deleteTask(long userId, long id) {
		// TODO Auto-generated method stub
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new UserNotFound(String.format("User Id %d not found", userId)));
		Task task = taskRepository.findById(id)
				.orElseThrow(() -> new TaskNotFound(String.format("Task Id %d not found", id)));
		if(user.getId()!= task.getUser().getId()) {
			throw new APIException(String.format("Task Id %d not belong to User Id %d", id,userId));
		}
		taskRepository.deleteById(id);
		
	}

}
