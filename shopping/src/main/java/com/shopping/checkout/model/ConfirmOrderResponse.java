package com.shopping.checkout.model;

public class ConfirmOrderResponse {

    private String message;

    public ConfirmOrderResponse() {

    }

    public ConfirmOrderResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
