package com.spares.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.spares.admin.entity.OrderDetailEntity;
import com.spares.admin.entity.OrderEntity;
import com.spares.admin.entity.UserEntity;
import com.spares.admin.entity.productEntity;
import com.spares.admin.repository.OrderDetailRepository;
import com.spares.admin.repository.OrderRepository;
import com.spares.admin.repository.ProductRepository;
import com.spares.admin.repository.UserRepository;
import com.spares.admin.service.MyUserDetailsService;



@RestController
public class UserController {

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private OrderDetailRepository orderDetailRepository;
	@Autowired
	private OrderRepository orderRepository;
	
	
	@Autowired
	private MyUserDetailsService myuserDetailsService;


	@GetMapping("/admin")
	@ResponseBody
	public String getString() {
		System.out.println("Inside Admin");
		return ("<h1>Welcome Admin</h1>");
	}
	

	@GetMapping("user/getAllUser")
	@ResponseBody
	public List<UserEntity> findAllUser(){
		return userRepo.findAll();
	}

	@PostMapping("user/saveUser")
	@ResponseBody
	public UserEntity saveUser(@RequestBody UserEntity user ){
		return userRepo.save(user);
	}


	@GetMapping("admin/getAllUser")
	public List<UserEntity> findAllUserByAdmin(@RequestHeader String Authorization ){
		return userRepo.findAll();
	}

	@PostMapping("dealer/saveProduct")
	@ResponseBody
	public productEntity saveproduct(@RequestBody productEntity product,@RequestHeader Integer userid ){
		UserEntity user=userRepo.getOne(userid);
		product.setProductDealername(user.getCompanyName());
		return productRepository.save(product);
	}

	@GetMapping("/user/geProduct")
	@ResponseBody
	public List<productEntity> findAllProduct(){
		return productRepository.findAll();
	}


	@PostMapping("/user/createorder")
	@ResponseBody
	public OrderEntity saveproduct(@RequestBody OrderEntity order,@RequestHeader Integer userid ){
		order.setUserid(userid.toString());
		return orderRepository.save(order);
	}
	@GetMapping("/user/getorder")
	@ResponseBody
	public OrderEntity getOrderByOrderID(@RequestHeader String Authorization ){
		return orderRepository.findById(1).get();
	}
	@GetMapping("/user/getorderdetail")
	@ResponseBody
	public List<OrderDetailEntity> getOrderdetail(@RequestHeader String Authorization ){
		return orderDetailRepository.findAll();
	}
}
