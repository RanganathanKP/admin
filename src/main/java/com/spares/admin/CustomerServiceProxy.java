package com.spares.admin;


import com.spares.admin.DTO.RatingDTO;
import com.spares.admin.entity.OrderDetailEntity;
import com.spares.admin.service.AdminFallBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name="customer-client",url = "localhost:8085",fallback = AdminFallBack.class)
public interface CustomerServiceProxy {


    @GetMapping("/sparecustomer/customer/allRating")
    @ResponseBody
    public ResponseEntity<List<RatingDTO>> getAllRating();



    @GetMapping("/sparecustomer/customer/getOrderDetail/{orderDetailID}")
    @ResponseBody
    public  ResponseEntity<OrderDetailEntity>getorderdetail(@PathVariable Integer orderDetailID);


}
