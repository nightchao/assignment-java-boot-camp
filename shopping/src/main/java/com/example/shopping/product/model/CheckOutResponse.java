package com.example.shopping.product.model;

public class CheckOutResponse {

    private String message;
    private String orderId;

    public CheckOutResponse() {

    }

    public CheckOutResponse(String message, String orderId) {
        this.message = message;
        this.orderId = orderId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
}
