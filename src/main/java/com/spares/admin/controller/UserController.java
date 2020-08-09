package com.spares.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spares.admin.entity.UserEntity;
import com.spares.admin.repository.UserRepository;

@RestController
public class UserController {

	@Autowired
	private UserRepository userRepo;
	
	@GetMapping("/getAllUser")
	public List<UserEntity> findAllUser(){
		return userRepo.findAll();
	}
}
