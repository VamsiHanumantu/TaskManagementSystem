package com.vamsi.taskproject.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vamsi.taskproject.payload.LoginDto;
import com.vamsi.taskproject.payload.UserDto;
import com.vamsi.taskproject.service.UserService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AuthenticationManager authenticationManager;

	//POST the user in the DB
	@PostMapping("/register")
	public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
		return new ResponseEntity<UserDto>(userService.createUser(userDto),HttpStatus.CREATED);
	}
	

	@PostMapping("/login")
	public ResponseEntity<String> loginUser(@RequestBody LoginDto loginDto){
		Authentication authentication = 
				authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		return new ResponseEntity<String>("User logged in successfully",HttpStatus.OK);
	}
}
