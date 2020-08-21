package com.spares.admin.service;

import com.spares.admin.CustomerServiceProxy;
import com.spares.admin.DTO.RatingDTO;
import com.spares.admin.DealerServiceProxy;
import com.spares.admin.entity.OrderDetailEntity;
import com.spares.admin.entity.ProductEntity;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class AdminFallBack implements DealerServiceProxy, CustomerServiceProxy {


    @Override
    public ResponseEntity<List<RatingDTO>> getAllRating() {
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<OrderDetailEntity> getorderdetail(Integer orderDetailID) {
        return new ResponseEntity<>(new OrderDetailEntity(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<ProductEntity>> findAllProduct() {
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK);
    }

    @Override
    public CollectionModel<ProductEntity> findlatestProduct() {
        return null;
    }
}
