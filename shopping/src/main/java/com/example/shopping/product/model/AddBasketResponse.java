package com.example.shopping.product.model;

public class AddBasketResponse {

    private String message;

    public AddBasketResponse() {

    }

    public AddBasketResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
