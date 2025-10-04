package com.example.safway.servicecaller;

import com.example.safway.donottouch.Cupon;
import com.example.safway.dto.CartDetails;
import com.example.safway.dto.CartReponce;
import com.example.safway.dto.Product;
import com.example.safway.entity.ProductEntity;
import com.example.safway.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.mongodb.core.aggregation.ArrayOperators;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CartService {
    private final ProductRepository repository;

    @Autowired
    public CartService(ProductRepository repository) {
        this.repository = repository;
    }

    @Autowired
    CuponServiceCaller serviceCaller;

    public CartReponce calculatecartItem(CartDetails cartDetails) {

        double totalPrice ;
        double finalPrice ;
        double discountAmount = 0;
        List<Integer> errorList;
        List<Integer> validList = new ArrayList<>();
        CartReponce responce = new CartReponce();


        Integer totalItemCount = cartDetails.getItems().entrySet().stream().map(Map.Entry::getValue).reduce(Integer::sum).orElse(0);
        List<Integer> listOfItemId = cartDetails.getItems().entrySet().stream().map(Map.Entry::getKey).map(Long::intValue).toList();

        List<ProductEntity> allById = repository.findAllById(listOfItemId);

        List<Integer> validProductIds = allById.stream().map(ProductEntity::getId).toList();
        errorList =  new ArrayList<>(listOfItemId);
        errorList.removeAll(validProductIds);

        totalPrice = allById.stream().map(ProductEntity::getPrice).reduce(Double::sum).orElse(0.0);
        discountAmount = getDiscountAmount(cartDetails, responce, discountAmount);
        finalPrice = totalPrice - discountAmount;


        responce.setItemCount(totalItemCount);
        responce.setInvalidItemCount(errorList.size());
        responce.setValidItemCount(validProductIds.size());
        responce.setErrorItemIdList(errorList);
        responce.setProcessedItemList(validList);
        responce.setTotalPrice(totalPrice);
        responce.setDiscountedPrice(discountAmount);
        responce.setFinalPrice(finalPrice);

        return responce;
    }

    private double getDiscountAmount(CartDetails cartDetails, CartReponce responce, double discountAmount) {
        List<Cupon> cuponDetails;
        if (cartDetails.getCoupon().isBlank()) {
            responce.setIsCouponApplied(false);
        } else {
            responce.setIsCouponApplied(true);
            cuponDetails = serviceCaller.getCuponByCuponName(cartDetails.getCoupon());
            discountAmount = cuponDetails.get(0).getDiscountedPrice();
        }
        return discountAmount;
    }

}
