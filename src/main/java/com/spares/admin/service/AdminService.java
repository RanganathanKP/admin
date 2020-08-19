package com.spares.admin.service;

import com.spares.admin.CustomerServiceProxy;
import com.spares.admin.DTO.RatingDTO;
import com.spares.admin.DealerServiceProxy;
import com.spares.admin.entity.OrderDetailEntity;
import com.spares.admin.entity.ProductEntity;
import com.spares.admin.entity.RatingEntity;
import com.spares.admin.exception.AdminException;
import com.spares.admin.exception.CustomerException;
import com.spares.admin.exception.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AdminService {

    @Autowired
    private DealerServiceProxy dealerServiceProxy;

    @Autowired
    private CustomerServiceProxy customerServiceProxy;

    public List<ProductEntity> viewAllProduct(Integer userID) {
        List<ProductEntity> response=new ArrayList();
        ResponseEntity<List<ProductEntity>> resourceResponse = dealerServiceProxy.findAllProduct();
        if (!Optional.ofNullable(resourceResponse).isPresent()) {
            throw new AdminException("Product not found");
        } else {
            response =resourceResponse.getBody();
                 if (CollectionUtils.isEmpty(response) || !Optional.ofNullable(response).isPresent()) {
                       throw new AdminException("Product not found");
                 }else {
                          if (Optional.ofNullable(userID).isPresent()) {
                                   response = response.stream().filter(product -> product.getProductUserID() == userID).collect(Collectors.toList());
                                         if (CollectionUtils.isEmpty(response)) {
                                             throw new AdminException("No product for this user");
                }
            }

        }
    }
        return response;
    }

    public List<ProductEntity> viewLatestProduct(Integer userID) {
        List<ProductEntity> response=new ArrayList<>(dealerServiceProxy.findlatestProduct().getContent());
        if(CollectionUtils.isEmpty(response)||!Optional.ofNullable(response).isPresent()){
            throw new AdminException("No Recent Product.");
        }else {
            if (Optional.ofNullable(userID).isPresent()) {
                response = response.stream().filter(product -> product.getProductUserID() == userID).collect(Collectors.toList());
                if (CollectionUtils.isEmpty(response)) {
                    throw new AdminException("No Recent product for this user.");
                }
            }
        }
        return response;
    }

    public List<RatingDTO> viewRating(Integer productid) {
        RatingDTO response = new RatingDTO();
        float overallRating = 0.0f;
        List<RatingDTO> ratingList = new ArrayList<>();
        ResponseEntity<List<RatingDTO>> responseRatings = customerServiceProxy.allRating();
        if (!Optional.ofNullable(responseRatings).isPresent()) {
            throw new AdminException("No Rating Found");
        } else {
            List<RatingDTO> ratings=responseRatings.getBody();
            if(!CollectionUtils.isEmpty(ratings)&&Optional.ofNullable(ratings).isPresent()) {
                if (Optional.ofNullable(productid).isPresent()) {
                    ratingList.add(ratings.stream().filter(product -> productid == product.getProductID()).findFirst().get());
                    return ratingList;
                } else {
                    return ratings;
                }
            }else{
                throw new AdminException("No Rating Found !");
            }
        }
    }

    public OrderDetailEntity getorderdetail(Integer orderDetailID){
        ResponseEntity<OrderDetailEntity> resourceResponse=customerServiceProxy.getorderdetail(orderDetailID);
        if(Optional.ofNullable(resourceResponse).isPresent()){
            OrderDetailEntity response= resourceResponse.getBody();
            if(Optional.ofNullable(response).isPresent()){
               return response;
            }else{
                throw new AdminException("No order found!");
            }
        }else{
            throw new AdminException("No order found!");
        }
    }






}
