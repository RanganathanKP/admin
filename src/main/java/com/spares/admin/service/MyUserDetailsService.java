package com.spares.admin.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.spares.admin.entity.UserEntity;
import com.spares.admin.repository.UserRepository;

@Service
public class MyUserDetailsService implements UserDetailsService{

	@Autowired
	private UserRepository userRepo;
	
	
	public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
		Optional<UserEntity> user= userRepo.findByUserName(name);
		return new User(user.get().getUserName(),user.get().getPassword(),(Collection<? extends GrantedAuthority>) new ArrayList());
	}

	
}