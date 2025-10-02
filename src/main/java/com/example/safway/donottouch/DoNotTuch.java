package com.example.safway.donottouch;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

@RestController
public final class DoNotTuch {


    RestTemplate restTemplate;

    @Autowired
    public DoNotTuch(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/api/coupons")
    public List<Cupon> getCoupons() throws IOException {
        Cupon[] coupons = getCupons();
        return Arrays.asList(coupons);
    }


    @GetMapping("/api/coupons/{cuponName}")
    public List<Cupon> getCoupons(@PathVariable String cuponName) throws IOException {
        Cupon[] coupons = getCupons();
        return Arrays.stream(coupons).filter(cupon -> cupon.getCouponName().equalsIgnoreCase(cuponName)).toList();

    }

    private static Cupon[] getCupons() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        InputStream inputStream = new ClassPathResource("cupons.json").getInputStream();

        JsonNode root = mapper.readTree(inputStream);
        JsonNode couponsNode = root.get("coupons");

        return mapper.treeToValue(couponsNode, Cupon[].class);
    }


}
