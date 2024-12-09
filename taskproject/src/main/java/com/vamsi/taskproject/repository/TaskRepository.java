package com.vamsi.taskproject.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vamsi.taskproject.model.Task;
import com.vamsi.taskproject.model.User;

public interface TaskRepository extends JpaRepository<Task, Long> {

	List<Task> findAllByUserId(long userId);

	

}