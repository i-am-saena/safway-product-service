package com.example.safway.controller;

import com.example.safway.dto.CartDetails;
import com.example.safway.dto.CartReponce;
import com.example.safway.dto.Product;
import com.example.safway.servicecaller.CartService;
import com.example.safway.servicecaller.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController {

    private final ProductService service;


    @Autowired
    public ProductController(ProductService service){
        this.service=service;
    }

    @Autowired
    CartService cartService;
    @PostMapping("/api/product/createProduct")
    public Product createProduct(@RequestBody @Valid Product product){

        service.addProduct(product);

        return product;
    }

    @GetMapping("/api/product/getAll")
    public List getAllProduct(){

        return service.getProduct();
    }

    @PostMapping("/api/cart/prepareCart")
    public CartReponce prepareCart(@RequestBody @Valid CartDetails cartDetails){

        return cartService.calculatecartItem(cartDetails);

    }
}
