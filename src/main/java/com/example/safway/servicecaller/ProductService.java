package com.example.safway.servicecaller;

import com.example.safway.dto.Product;
import com.example.safway.entity.ProductEntity;
import com.example.safway.enums.ItemCategory;
import com.example.safway.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository repository;

    @Autowired
    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public void  addProduct(Product product){

       List<String>categoryList= Arrays.stream(ItemCategory.values()).map(m->m.name()).collect(Collectors.toList());

        if(!categoryList.contains(product.getCategory())) {
            throw new RuntimeException("category is invalid ::"+product.getCategory());
        }

            ProductEntity entity = new ProductEntity();
            entity.setName(product.getName());
            entity.setPrice(product.getPrice());
            entity.setCategory(product.getCategory());
            repository.save(entity);


    }

    public List<ProductEntity> getProduct(){
       return repository.findAll();

    }

}
