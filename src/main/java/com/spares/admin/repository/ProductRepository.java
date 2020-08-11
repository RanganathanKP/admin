package com.spares.admin.repository;

import com.spares.admin.entity.productEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<productEntity, Integer>{

}
