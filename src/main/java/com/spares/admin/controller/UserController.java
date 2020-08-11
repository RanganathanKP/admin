package com.spares.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.spares.admin.entity.AuthenticationRequest;
import com.spares.admin.entity.AuthenticationResponse;
import com.spares.admin.entity.OrderDetailEntity;
import com.spares.admin.entity.OrderEntity;
import com.spares.admin.entity.UserEntity;
import com.spares.admin.entity.productEntity;
import com.spares.admin.repository.OrderDetailRepository;
import com.spares.admin.repository.OrderRepository;
import com.spares.admin.repository.ProductRepository;
import com.spares.admin.repository.UserRepository;
import com.spares.admin.service.MyUserDetailsService;
import com.spares.admin.util.JwtUtil;



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
	private AuthenticationManager authManager;
	
	@Autowired
	private MyUserDetailsService myuserDetailsService;
	
	@Autowired
	private JwtUtil jwtUtil;



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
	
	@PostMapping("/authenticate")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception{
		try {
		authManager.authenticate(
				new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
				);
		}catch(BadCredentialsException e) {
			throw new Exception("Incorrect Username and passowrd");
		}
		
		final UserDetails userDetails = myuserDetailsService
				.loadUserByUsername(authenticationRequest.getUsername());
		
		final String jwt = jwtUtil.generateToken(userDetails);
		
		return ResponseEntity.ok(new AuthenticationResponse(jwt));
	}

}
