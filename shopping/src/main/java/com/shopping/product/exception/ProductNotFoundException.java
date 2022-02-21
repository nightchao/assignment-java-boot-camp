package com.shopping.product.exception;

public class ProductNotFoundException extends RuntimeException {

    public ProductNotFoundException(int productId) {
        super("productId " + productId + " not found");
    }
}
