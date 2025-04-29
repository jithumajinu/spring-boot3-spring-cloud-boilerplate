package com.openapi.cloud.core.repository;

import com.openapi.cloud.core.model.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional(readOnly = true)
public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {

    List<Product> findByNameContainingIgnoreCase(String name);

    List<Product> findAllByDeleteFlag(boolean status);

    @Query("SELECT e FROM Product e WHERE e.deleteFlag = false")
    List<Product> findAllProduct();
}
