package com.vamsi.taskproject.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vamsi.taskproject.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByEmail(String email);

}
