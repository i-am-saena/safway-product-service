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
     private  final ProductRepository repository;

    @Autowired
    public CartService(ProductRepository repository){
        this.repository=repository;
    }

    @Autowired
    CuponServiceCaller serviceCaller;

    public CartReponce calculatecartItem(CartDetails cartDetails){
        int validItemCount = 0;
        int invalidItemCount = 0;
        double totalPrice=0;
        double finalPrice=0;
        double discountAmount=0;
        int quantity =0;
        List<Double> cuponPrice =new ArrayList<>();;
        List<Cupon> cuponDetails;
        List<Integer> errorList=new ArrayList<>();
        List<Integer> validList=new ArrayList<>();

        CartReponce responce = new CartReponce();

        if(cartDetails.getCoupon().isBlank()){
            responce.setIsCouponApplied(false);
        }else{
            responce.setIsCouponApplied(true);
             cuponDetails= serviceCaller.getCuponByCuponName(cartDetails.getCoupon());
             cuponPrice=cuponDetails.stream().map(m->m.getDiscountedPrice()).toList();
            discountAmount=cuponPrice.get(0);
        }

       Integer totalItemCount= cartDetails.getItems().entrySet().stream().map(Map.Entry::getValue).reduce((a, b)->a+b).get();
        List<Integer> listOfItemId = cartDetails.getItems().entrySet().stream().map(Map.Entry::getKey).map(m->m.intValue()).toList();
        responce.setItemCount(totalItemCount);

        for(Integer itemId:listOfItemId){

            Optional<ProductEntity> product=repository.findById(itemId);

            if(product.isEmpty()){
                   invalidItemCount =invalidItemCount+1;
                   errorList.add(itemId);
            }else{
                validItemCount =validItemCount+1;
                validList.add(itemId);
               quantity= cartDetails.getItems().get(itemId.longValue());
               totalPrice += quantity * product.get().getPrice();
            }
        }


        finalPrice=    totalPrice- discountAmount ;




        responce.setInvalidItemCount(invalidItemCount);
        responce.setValidItemCount(validItemCount);
        responce.setErrorItemIdList(errorList);
        responce.setProcessedItemList(validList);
        responce.setTotalPrice(totalPrice);
        responce.setDiscountedPrice(discountAmount);
        responce.setFinalPrice(finalPrice);

        return responce;
    }

}
