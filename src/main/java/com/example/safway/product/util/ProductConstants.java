package com.example.safway.product.util;

/**
 * Contains constants used across this project
 */
public class ProductConstants {

    /**
     * Private constructor toi prevent instantiation.
     */
    private ProductConstants(){

    }

    public static final String ROOT_URL = "/safeway";
    public static final String ADD_PRODUCT = "/addProduct";
    public static final String UPDATE_PRODUCT = "/updateProduct";
    public  static final String FETCH_PRODUCT = "/getById/{id}";

}
