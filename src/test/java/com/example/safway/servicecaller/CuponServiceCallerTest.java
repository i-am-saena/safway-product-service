package com.example.safway.servicecaller;

import com.example.safway.donottouch.Cupon;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

class CuponServiceCallerTest {

    private  MockRestServiceServer mockServer;
    private final RestTemplate restTemplate = new RestTemplate();
    private final CuponServiceCaller service = new CuponServiceCaller(restTemplate);

    @BeforeEach
    void setup(){

        mockServer = MockRestServiceServer.createServer(restTemplate);

    }
    @Test
    void testGetCuponAll(){
        mockServer.expect(requestTo("http://localhost:8080/api/coupons"))
                .andRespond(withSuccess("""
                        [
                          {
                            "couponName": "SAVE10",
                            "couponCategory": "Electronics",
                            "discountedPrice": 90.0
                          },
                          {
                            "couponName": "FASHION20",
                            "couponCategory": "Clothing",
                            "discountedPrice": 80.0
                          }
                        ]
                        """, MediaType.APPLICATION_JSON));
        List<Cupon> allCupons = service.getAllCupons();
        Assertions.assertFalse(allCupons.isEmpty());

    }

    @Test
    void testGetCuponByName(){
        mockServer.expect(requestTo("http://localhost:8080/api/coupons/SAVE10"))
                .andRespond(withSuccess("""
                        [
                          {
                            "couponName": "SAVE10",
                            "couponCategory": "Electronics",
                            "discountedPrice": 90.0
                          }
                        ]
                        """, MediaType.APPLICATION_JSON));
        List<Cupon> allCupons = service.getCuponByCuponName("SAVE10");
        assertEquals(1, allCupons.size());

    }

}