package com.spares.admin;


import com.spares.admin.entity.ProductEntity;
import com.spares.admin.service.AdminFallBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@FeignClient(name="dealer-client",url = "localhost:8083",fallback = AdminFallBack.class)
public interface DealerServiceProxy {

    @GetMapping("/findAllProduct")
    @ResponseBody
    public ResponseEntity<List<ProductEntity>>findAllProduct();

    @GetMapping("productEntities/search/findlatestProduct")
    @ResponseBody
    public CollectionModel<ProductEntity> findlatestProduct();


}
