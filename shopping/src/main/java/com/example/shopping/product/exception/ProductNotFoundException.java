package com.example.shopping.product.exception;

public class ProductNotFoundException extends RuntimeException {

    public ProductNotFoundException(int product) {
        super("productId " + product + " not found");
    }
}
