package com.vamsi.taskproject.security;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.vamsi.taskproject.exception.UserNotFound;
import com.vamsi.taskproject.model.User;
import com.vamsi.taskproject.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepo;
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		User user = userRepo.findByEmail(email).orElseThrow(
				()-> new UserNotFound(String.format("User with email : %s not found",email)));
		
		Set<String> roles= new HashSet<>();
		roles.add("ROLE_ADMIN");
		return new org.springframework.security.core.userdetails.User(user.getEmail(),user.getPassword(),userAuthorities(roles));
	}

	private Collection<? extends GrantedAuthority> userAuthorities(Set<String> roles){
		return roles.stream().map(
				role-> new SimpleGrantedAuthority(role)
				).collect(Collectors.toList());
	}
}
