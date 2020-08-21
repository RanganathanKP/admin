package com.spares.admin.service;

import com.spares.admin.CustomerServiceProxy;
import com.spares.admin.DTO.PurchaseOrderDTO;
import com.spares.admin.DTO.RatingDTO;
import com.spares.admin.DealerServiceProxy;
import com.spares.admin.entity.OrderDetailEntity;
import com.spares.admin.entity.OrderEntity;
import com.spares.admin.entity.ProductEntity;
import com.spares.admin.entity.UserEntity;
import com.spares.admin.exception.AdminException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class AdminServiceTest {
    @Mock
    private DealerServiceProxy dealerServiceProxy;

    @Mock
    private CustomerServiceProxy customerServiceProxy;

    private AdminService adminService;

    @Before
    public void init(){
        adminService= new AdminService( dealerServiceProxy,  customerServiceProxy);
    }

    @Test
    public void viewAllProductTest(){
        ResponseEntity<List<ProductEntity>> list = new ResponseEntity<>(getprods(), HttpStatus.OK);
        when(dealerServiceProxy.findAllProduct()).thenReturn(list);
        List<ProductEntity> response = adminService.viewAllProduct(1);
        assertEquals(1,response.get(0).getProductId());
    }

    @Test
    public void viewAllProductTestTwo(){
        ResponseEntity<List<ProductEntity>> list = new ResponseEntity(new ArrayList(), HttpStatus.OK);
        when(dealerServiceProxy.findAllProduct()).thenReturn(list);
        try {
            List<ProductEntity> response = adminService.viewAllProduct(1);
        }catch(AdminException e){
            assertEquals("Product not found",e.getMessage());
        }
    }

    @Test
    public void viewAllProductTestthree(){
        ResponseEntity<List<ProductEntity>> list = new ResponseEntity<>(getprods(), HttpStatus.OK);
        when(dealerServiceProxy.findAllProduct()).thenReturn(list);
        try {
            List<ProductEntity> response = adminService.viewAllProduct(9);
        }catch(AdminException e){
            assertEquals("No product for this user",e.getMessage());
        }
    }

    @Test
    public void viewLatestProductTest(){
        Collection c= getprods();
        CollectionModel response = new CollectionModel<>(c);
        ResponseEntity<List<ProductEntity>> list = new ResponseEntity<>(getprods(), HttpStatus.OK);
        when(dealerServiceProxy.findlatestProduct()).thenReturn(response);
        List<ProductEntity>  responseService = adminService.viewLatestProduct(1);
        assertEquals(1,responseService.get(0).getProductId());
    }


    @Test
    public void viewLatestProductTesttwo(){
        Collection c= new ArrayList();
        CollectionModel response = new CollectionModel<>(c);
        ResponseEntity<List<ProductEntity>> list = new ResponseEntity<>(getprods(), HttpStatus.OK);
        when(dealerServiceProxy.findlatestProduct()).thenReturn(response);
        try {
            List<ProductEntity> responseService = adminService.viewLatestProduct(1);
        }catch(AdminException e){
            assertEquals("No Recent Product.",e.getMessage());
        }

    }

    @Test
    public void viewLatestProductTestthree(){
        Collection c= getprods();
        CollectionModel response = new CollectionModel<>(c);
        ResponseEntity<List<ProductEntity>> list = new ResponseEntity<>(getprods(), HttpStatus.OK);
        when(dealerServiceProxy.findlatestProduct()).thenReturn(response);
        try {
            List<ProductEntity> responseService = adminService.viewLatestProduct(9);
        }catch(AdminException e){
            assertEquals("No Recent product for this user.",e.getMessage());
        }

    }

    @Test
    public void viewRatingTest(){
        ResponseEntity<List<RatingDTO>> list = new ResponseEntity<>(getratingdtos(), HttpStatus.OK);
        when(customerServiceProxy.getAllRating()).thenReturn(list);
        List<RatingDTO> responseService = adminService.viewRating(1);
        assertEquals(1,responseService.get(0).getProductID());
    }

    @Test
    public void viewRatingTesttwo(){
        ResponseEntity<List<RatingDTO>> list = new ResponseEntity<>(getratingdtos(), HttpStatus.OK);
        when(customerServiceProxy.getAllRating()).thenReturn(list);
        List<RatingDTO> responseService = adminService.viewRating(null);
        assertEquals(1,responseService.get(0).getProductID());
    }
    @Test
    public void viewRatingTestthree(){
        ResponseEntity<List<RatingDTO>> list = new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK);
        when(customerServiceProxy.getAllRating()).thenReturn(list);
        try {
            List<RatingDTO> responseService = adminService.viewRating(1);
        }catch(AdminException e){
            assertEquals("No Rating Found !",e.getMessage());
        }

    }

    @Test
    public void getorderdetailTest(){
     ResponseEntity<OrderDetailEntity> list = new ResponseEntity(getdetails().get(0), HttpStatus.OK);
     when(customerServiceProxy.getorderdetail(any())).thenReturn(list);
     OrderDetailEntity responseService = adminService.getorderdetail(1);
     assertEquals(1,responseService.getProductID());
}

    @Test
    public void getorderdetailTesttwo(){
        ResponseEntity<OrderDetailEntity> list = new ResponseEntity(new OrderDetailEntity(), HttpStatus.OK);
        when(customerServiceProxy.getorderdetail(any())).thenReturn(list);
        try {
            OrderDetailEntity responseService = adminService.getorderdetail(1);
        }catch(AdminException e){
            assertEquals("No order found!",e.getMessage());
        }
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
