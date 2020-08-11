package com.spares.admin.controller;

import java.util.List;

import com.spares.admin.entity.OrderDetailEntity;
import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.spares.admin.entity.UserEntity;
import com.spares.admin.entity.OrderEntity;
import com.spares.admin.repository.UserRepository;
import com.spares.admin.entity.productEntity;
import com.spares.admin.repository.ProductRepository;
import com.spares.admin.repository.OrderDetailRepository;
import com.spares.admin.repository.OrderRepository;



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



	@GetMapping("/getAllUser")
	@ResponseBody
	public List<UserEntity> findAllUser(@RequestHeader String Authorization ){
		return userRepo.findAll();
	}

	@PostMapping("/saveUser")
	@ResponseBody
	public UserEntity saveUser(@RequestBody UserEntity user ){
		return userRepo.save(user);
	}



	@PostMapping("/saveProduct")
	@ResponseBody
	public productEntity saveproduct(@RequestBody productEntity product,@RequestHeader Integer userid ){
		UserEntity user=userRepo.getOne(userid);
		product.setProductDealername(user.getCompanyName());
		return productRepository.save(product);
	}

	@GetMapping("/geProduct")
	@ResponseBody
	public List<productEntity> findAllProduct(@RequestHeader String Authorization ){
		return productRepository.findAll();
	}


	@PostMapping("/createorder")
	@ResponseBody
	public OrderEntity saveproduct(@RequestBody OrderEntity order,@RequestHeader Integer userid ){
		order.setUserid(userid.toString());
		return orderRepository.save(order);
	}
	@GetMapping("/getorder")
	@ResponseBody
	public OrderEntity getOrderByOrderID(@RequestHeader String Authorization ){
		return orderRepository.findById(1).get();
	}
	@GetMapping("/getorderdetail")
	@ResponseBody
	public List<OrderDetailEntity> getOrderdetail(@RequestHeader String Authorization ){
		return orderDetailRepository.findAll();
	}

}
