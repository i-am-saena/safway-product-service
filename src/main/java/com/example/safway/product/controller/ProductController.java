package com.example.safway.product.controller;

import com.example.safway.product.dto.UpdateProductRequest;
import com.example.safway.product.dto.Product;
import com.example.safway.product.repository.entity.ProductEntity;
import com.example.safway.product.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import static com.example.safway.product.util.ProductConstants.*;
import static com.example.safway.product.util.ProductConstants.ROOT_URL;

/**
 * Contains all endpoints to manage products.
 */
@RestController
@RequestMapping(ROOT_URL)
public class ProductController {

    ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    /**
     *
     * @param product
     * @return
     */
    @PostMapping(ADD_PRODUCT)
    public ProductEntity addProduct(@RequestBody @Valid Product product) {
        return productService.addProductDetails(product);
    }

    @PutMapping(UPDATE_PRODUCT)
    public ProductEntity updateProduct(@RequestBody @Valid UpdateProductRequest product) {
        return productService.updateProductDetails(product);
    }

    @GetMapping(FETCH_PRODUCT)
    public Optional<ProductEntity> productDetailsGetById(@PathVariable @Valid String id){

        return productService.fetchProductById(id);
    }

}
