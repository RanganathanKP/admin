package com.spares.admin.service;

import com.spares.admin.CustomerServiceProxy;
import com.spares.admin.DTO.RatingDTO;
import com.spares.admin.DTO.Ratingv2DTO;
import com.spares.admin.DealerServiceProxy;
import com.spares.admin.entity.OrderDetailEntity;
import com.spares.admin.entity.ProductEntity;
import com.spares.admin.exception.AdminException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AdminService {

    @Autowired
    private DealerServiceProxy dealerServiceProxy;

    @Autowired
    private CustomerServiceProxy customerServiceProxy;

    public AdminService(DealerServiceProxy dealerServiceProxy, CustomerServiceProxy customerServiceProxy) {
        this.dealerServiceProxy = dealerServiceProxy;
        this.customerServiceProxy = customerServiceProxy;
    }

    public List<ProductEntity> viewAllProduct(Integer userID) {
        ResponseEntity<List<ProductEntity>> resourceResponse = dealerServiceProxy.findAllProduct();
        List<ProductEntity> response=resourceResponse.getBody();
        if (CollectionUtils.isEmpty(response)) {
                throw new AdminException("Product not found");
            } else {
                if (Optional.ofNullable(userID).isPresent()) {
                    response = response.stream().filter(product -> product.getProductUserID().equals(userID)).collect(Collectors.toList());
                    if (CollectionUtils.isEmpty(response)) {
                        throw new AdminException("No product for this user");
                    }
            }
        }
        return response;
    }

    public List<ProductEntity> viewLatestProduct(Integer userID) {
        Collection<ProductEntity> responseCollection = Optional.ofNullable(dealerServiceProxy.findlatestProduct().getContent()).orElseThrow(() -> new AdminException("No Recent Product."));
            List<ProductEntity> response = new ArrayList<>(responseCollection);
            if (CollectionUtils.isEmpty(response)) {
                throw new AdminException("No Recent Product.");
            } else {
                if (Optional.ofNullable(userID).isPresent()) {
                    response = response.stream().filter(product -> product.getProductUserID().equals(userID)).collect(Collectors.toList());
                    if (CollectionUtils.isEmpty(response)) {
                        throw new AdminException("No Recent product for this user.");
                    }
                }
            }
            return response;
        }

    public List<RatingDTO> viewRating(Integer productid) {
        List<RatingDTO> ratingList = new ArrayList<>();
            List<RatingDTO> ratings = customerServiceProxy.getAllRating().getBody();
            if (!CollectionUtils.isEmpty(ratings)) {

                if (Optional.ofNullable(productid).isPresent()) {
                    Optional<RatingDTO> rate = ratings.stream().filter(product -> productid.equals(product.getProductID())).findFirst();
                    if(rate.isPresent()){
                        ratingList.add(rate.get());
                        return ratingList;
                    }else{
                        return ratings;
                    }

                } else {
                    return ratings;
                }
            } else {
                throw new AdminException("No Rating Found !");
            }
    }

    public List<Ratingv2DTO> viewRatingv2(Integer productid) {
        List<Ratingv2DTO> v2= new ArrayList<>();
        List<RatingDTO>response=viewRating(productid);
        response.forEach(rate->{
            Ratingv2DTO rv2=new Ratingv2DTO();
            rv2.setProductID(rate.getProductID());
            rv2.setRating(rate.getRating());
            if(rate.getRating()>7){
                rv2.setCustomerPrefered(true);
            }
            v2.add(rv2);
        });
        return v2;
    }

    public OrderDetailEntity getorderdetail(Integer orderDetailID) {
            OrderDetailEntity response = customerServiceProxy.getorderdetail(orderDetailID).getBody();
            if (Optional.ofNullable(response).isPresent()&&Optional.ofNullable(response.getOrderdetailId()).isPresent()) {
                return response;
            } else {
                throw new AdminException("No order found!");
            }
    }


}
