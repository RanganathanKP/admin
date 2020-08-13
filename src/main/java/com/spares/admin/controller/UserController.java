package com.spares.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.spares.admin.entity.UserEntity;
import com.spares.admin.repository.UserRepository;
import com.spares.admin.service.MyUserDetailsService;



@RestController
public class UserController {

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private MyUserDetailsService myuserDetailsService;

	@GetMapping("user/getAllUser")
	@ResponseBody
	public List<UserEntity> findAllUser(){
		return userRepo.findAll();
	}

	@PostMapping("/saveUser")
	@ResponseBody
	public UserEntity saveUser(@RequestBody UserEntity user ){
		return userRepo.save(user);
	}

}
