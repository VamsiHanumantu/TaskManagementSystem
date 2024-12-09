package com.vamsi.taskproject.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.vamsi.taskproject.model.User;
import com.vamsi.taskproject.payload.UserDto;
import com.vamsi.taskproject.repository.UserRepository;
import com.vamsi.taskproject.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Override
	public UserDto createUser(UserDto userDto) {
		// TODO Auto-generated method stub
		userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
		User user = userDtoToEntity(userDto);
	    User savedUser=userRepository.save(user);
	    
		return entityToUserDto(savedUser);
	}
	
	private User userDtoToEntity(UserDto userDto) {
		User user = new User();
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		
		return user;
	}
	
	private UserDto entityToUserDto(User savedUser) {
		UserDto userDto = new UserDto();
		userDto.setId(savedUser.getId());
		userDto.setName(savedUser.getName());
		userDto.setEmail(savedUser.getEmail());
		userDto.setPassword(savedUser.getPassword());
		
		return userDto;
	}
 
}
