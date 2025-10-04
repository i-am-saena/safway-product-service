package com.example.safway.dto;

import lombok.Data;

import java.util.List;

@Data
public class CartReponce {

        private Integer itemCount;
        private Double totalPrice;
        private Double discountedPrice;
        private Double finalPrice;
        private Boolean isCouponApplied;
        private List<Integer> errorItemIdList;
        private List<Integer> processedItemList;
        private Integer validItemCount;
        private Integer invalidItemCount;


}
