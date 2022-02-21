package com.exception;

public class BasketItemNotFoundException extends RuntimeException {

    public BasketItemNotFoundException(int userId) {
        super("Product in basket not found userId: " + userId);
    }
}
