package com.example.safway.product.repository;

import com.example.safway.product.repository.entity.ProductEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends MongoRepository<ProductEntity,String> {

    @Query("{\"productName\":?0}")
    ProductEntity getProduct(String name);
}
