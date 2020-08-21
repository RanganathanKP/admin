package com.spares.admin.controller;

import com.spares.admin.DTO.PurchaseOrderDTO;
import com.spares.admin.DTO.RatingDTO;
import com.spares.admin.entity.OrderDetailEntity;
import com.spares.admin.entity.OrderEntity;
import com.spares.admin.entity.ProductEntity;
import com.spares.admin.entity.UserEntity;
import com.spares.admin.service.AdminService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)

public class AdminControllerTest {
    @Mock
    private ControllerAdvisor controllerAdvisor;

    @Mock
    private AdminService adminService;

    @InjectMocks
    private UserController userController;

    @Test
    public void findAllProduct(){
        when(adminService.viewAllProduct(any())).thenReturn(getprods());
        ResponseEntity<List<ProductEntity>> serviceResponse = userController.findAllProduct(1);
        assertEquals(1,serviceResponse.getBody().get(0).getProductId());
    }

    @Test
    public void findNewProduct(){
        when(adminService.viewLatestProduct(any())).thenReturn(getprods());
        ResponseEntity<List<ProductEntity>> serviceResponse = userController.findNewProduct(1);
        assertEquals(1,serviceResponse.getBody().get(0).getProductId());
    }

    @Test
    public void getRatingByProductID(){
        when(adminService.viewRating(any())).thenReturn(getratingdtos());
        ResponseEntity<List<RatingDTO>>  serviceResponse = userController.getRatingByProductID(1);
        assertEquals(1,serviceResponse.getBody().get(0).getProductID());
    }

    @Test
    public void getorderdetail(){
        when(adminService.getorderdetail(any())).thenReturn(getdetails().get(0));
        ResponseEntity<OrderDetailEntity> serviceResponse = userController.getorderdetail(1);
        assertEquals(1,serviceResponse.getBody().getProductID());
    }

    public List<RatingDTO> getratingdtos(){
        RatingDTO rating= new RatingDTO();
        List<RatingDTO>ratings= new ArrayList();
        rating.setProductID(1);
        ratings.add(rating);
        return ratings;
    }




    public List<PurchaseOrderDTO> getpos(){
        List<PurchaseOrderDTO> pos= new ArrayList();
        PurchaseOrderDTO po= new PurchaseOrderDTO();
        po.setProductid(1);
        po.setQuantity(1);
        pos.add(po);
        return pos;
    }

    public Optional<UserEntity> getuser(){
        UserEntity user= new UserEntity();
        user.setUserId(1);
        user.setUserName("user");
        user.setUserRole("dealer");
        user.setPassword("cGFzcw==");
        return Optional.of(user);
    }

    public List<OrderEntity> getorder(){
        List<OrderEntity> orders= new ArrayList();
        OrderEntity order = new OrderEntity();
        order.setOrderId(1);
        order.setUserid(1);
        order.setOrdertotaltmount(200);
        order.setOrderDetailEntityList(getdetails());
        orders.add(order);
        return orders;
    }

    public List<OrderDetailEntity>getdetails(){
        List<OrderDetailEntity> orders= new ArrayList();
        OrderDetailEntity order = new OrderDetailEntity();
        order.setDealerID(1);
        order.setOrderDetailStatus("PLACED");
        order.setUserID(1);
        order.setProductID(1);
        order.setOrderdetailId(1);
        order.setOrderDetailQuantity(1);
        order.setOrderID(1);
        orders.add(order);
        return orders;
    }

    public List<ProductEntity>getprods() {
        List<ProductEntity> prods = new ArrayList();
        ProductEntity prod = new ProductEntity();
        prod.setProductId(1);
        prod.setProductAmount(100);
        prod.setProductUserID(1);
        prod.setProductName("prod name");
        prod.setProductDescription("prod desc");
        prods.add(prod);
        return prods;
    }
}
