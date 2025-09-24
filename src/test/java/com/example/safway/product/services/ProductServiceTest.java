package com.example.safway.product.services;

import com.example.safway.product.dto.Product;
import com.example.safway.product.dto.UpdateProductRequest;
import com.example.safway.product.repository.entity.ProductEntity;
import com.example.safway.product.repository.ProductRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @InjectMocks
    ProductService service;

    @Mock
    ProductRepository repository;

    private static ProductEntity entity;
    private static Product product;
    private static UpdateProductRequest updateProductRequest;

    @BeforeAll
    static void testSetup() {

        updateProductRequest = new UpdateProductRequest();
        updateProductRequest.setId("testid");
        updateProductRequest.setProductName("test");
        updateProductRequest.setDescription("test product");
        updateProductRequest.setProductType("productType");
        updateProductRequest.setBrand("brand");

        entity = new ProductEntity();
        entity.setId("testid");
        entity.setProductName("test");
        entity.setDescription("test product");
        entity.setProductType("productType");
        entity.setBrand("brand");


        product = new Product();
        product.setProductName("test");
        product.setDescription("test product");
        product.setProductType("productType");
        product.setBrand("brand");


    }

    @Test
    @DisplayName("Product should get saved")
    void addProductDetailsTest() {

        when(repository.getProduct(product.getProductName())).thenReturn(null);
        when(repository.save(any(ProductEntity.class))).thenReturn(new ProductEntity());
        service.addProductDetails(product);
        verify(repository).save(any(ProductEntity.class));
    }

    @Test
    @DisplayName("Should through exception if product exist")
    void TestProductAlreadyExist() {

        when(repository.getProduct(product.getProductName())).thenReturn(new ProductEntity());
        RuntimeException ex = assertThrows(IllegalArgumentException.class,
                () -> service.addProductDetails(product));
        assertEquals("Product Already Exists", ex.getMessage());

    }


    @Test
    @DisplayName("Record should get updated")
    void updateProductDetailsTest() {

        when(repository.findById(updateProductRequest.getId())).thenReturn(Optional.of(entity));
        when(repository.save(entity)).thenReturn(entity);

        service.updateProductDetails(updateProductRequest);

        verify(repository).save(entity);


    }

    @Test
    @DisplayName("should through error if no product found with supplied product id")
    void TestProductNotExistForUpdate() {

        when(repository.findById(updateProductRequest.getId())).thenReturn(Optional.empty());
        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> service.updateProductDetails(updateProductRequest));
        assertEquals(product.getProductName() + " Data is not exist", ex.getMessage());

    }


}