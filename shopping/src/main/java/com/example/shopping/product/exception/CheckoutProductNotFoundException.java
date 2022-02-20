package com.example.shopping.product.exception;

public class CheckoutProductNotFoundException extends RuntimeException {

    public CheckoutProductNotFoundException(int userId) {
        super("Cannot checkout product userId: " + userId);
    }
}
