package com.example.safway.servicecaller;

import com.example.safway.donottouch.Cupon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class CuponServiceCaller {

    RestTemplate restTemplate;

    @Autowired
    public CuponServiceCaller(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<Cupon> getAllCupons(){

        ResponseEntity<List<Cupon>> response = restTemplate.exchange(
                "http://localhost:8080/api/coupons",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Cupon>>() {}
        );

        return response.getBody();


    }


    public  List<Cupon>  getCuponByCuponName(String cuponname){

        ResponseEntity<List<Cupon>> response = restTemplate.exchange(
                "http://localhost:8080/api/coupons/"+cuponname,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Cupon>>() {}
        );

        return response.getBody();
    }


}
