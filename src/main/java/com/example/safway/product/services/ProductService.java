package com.example.safway.product.services;

import com.example.safway.product.dto.Product;
import com.example.safway.product.dto.UpdateProductRequest;
import com.example.safway.product.repository.entity.ProductEntity;
import com.example.safway.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * Contains logic to process products.
 */
@Service
public class ProductService {

    ProductRepository repository;

    @Autowired
    public ProductService( ProductRepository repository){
        this.repository = repository;
    }

    /**
     * Receives validated product details and saves to the DB.
     *
     * @param product- Request body containing product details
     * @return -  Returns saved product details with product id
     */
    public ProductEntity addProductDetails(Product product){

        ProductEntity checkData=  repository.getProduct(product.getProductName());

        if (checkData !=null){
            throw new IllegalArgumentException ("Product Already Exists");
        }
        ProductEntity entity=new ProductEntity();
        entity.setProductName(product.getProductName());
        entity.setProductType(product.getProductType());
        entity.setBrand(product.getBrand());
        entity.setDescription(product.getDescription());

       return repository.save(entity);

    }

    /**
     * Updates product details based on product ID. Throws NoSuchElementException if no product is found with supplied product id.
     * @param product - Updated product details
     * @return - Updated product
     */
    public ProductEntity updateProductDetails(UpdateProductRequest product) {

        Optional<ProductEntity> optionalEntity = repository.findById(product.getId());
        if (optionalEntity.isEmpty()) {
            throw new NoSuchElementException(product.getProductName() + " Data is not exist");
        }

        ProductEntity entity = optionalEntity.get();
        entity.setProductName(product.getProductName());
        entity.setBrand(product.getBrand());
        entity.setDescription(product.getDescription());
        entity.setProductType(product.getProductType());

        return repository.save(entity);

    }

}
