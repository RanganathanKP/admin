package com.spares.admin.controller;

import java.util.List;

import com.spares.admin.DTO.RatingDTO;
import com.spares.admin.entity.OrderDetailEntity;
import com.spares.admin.entity.ProductEntity;
import com.spares.admin.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.spares.admin.entity.UserEntity;
import com.spares.admin.repository.UserRepository;
import com.spares.admin.service.MyUserDetailsService;



@RestController
public class UserController {

	@Autowired
	private UserRepository userRepo;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private AdminService adminService;


	@Autowired
	private MyUserDetailsService myuserDetailsService;


	@GetMapping("/findAllProduct")
	@ResponseBody
	public ResponseEntity<List<ProductEntity>> findAllProduct(@RequestParam(value="userID",required = false) Integer userid){
		List<ProductEntity> productResponse= adminService.viewAllProduct(userid);
		return new ResponseEntity<>(productResponse, HttpStatus.OK);
	}

	@GetMapping("/findNewProduct")
	@ResponseBody
	public ResponseEntity<List<ProductEntity>> findNewProduct(@RequestParam(value="userID",required = false) Integer userid){
		List<ProductEntity> productResponse= adminService.viewLatestProduct(userid);
		return new ResponseEntity<>(productResponse, HttpStatus.OK);
	}

	@GetMapping("/getrating")
	@ResponseBody
	public  ResponseEntity<List<RatingDTO>> getRatingByProductID(@RequestParam(value="productid",required = false) Integer productid  ){
		List<RatingDTO> response= adminService.viewRating(productid);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/getOrderDetail/{orderDetailID}")
	@ResponseBody
	public  ResponseEntity<OrderDetailEntity>getorderdetail(@PathVariable int orderDetailID){
		OrderDetailEntity response= adminService.getorderdetail(orderDetailID);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}
