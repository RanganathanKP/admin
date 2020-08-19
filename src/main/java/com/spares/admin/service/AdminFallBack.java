package com.spares.admin.service;

import com.spares.admin.CustomerServiceProxy;
import com.spares.admin.DTO.RatingDTO;
import com.spares.admin.DealerServiceProxy;
import com.spares.admin.entity.OrderDetailEntity;
import com.spares.admin.entity.ProductEntity;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class AdminFallBack implements DealerServiceProxy, CustomerServiceProxy {


    @Override
    public ResponseEntity<List<RatingDTO>> allRating() {
        return null;
    }

    @Override
    public ResponseEntity<OrderDetailEntity> getorderdetail(Integer orderDetailID) {
        return null;
    }

    @Override
    public ResponseEntity<List<ProductEntity>> findAllProduct() {
        return null;
    }

    @Override
    public CollectionModel<ProductEntity> findlatestProduct() {
        return null;
    }
}
