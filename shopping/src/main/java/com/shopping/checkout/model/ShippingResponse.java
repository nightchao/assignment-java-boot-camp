package com.shopping.checkout.model;

public class ShippingResponse {

    private boolean isEms;
    private String deliveryTime;

    public ShippingResponse() {

    }

    public ShippingResponse(boolean isEms, String deliveryTime) {
        this.isEms = isEms;
        this.deliveryTime = deliveryTime;
    }

    public String getDeliveryTime() {
        return deliveryTime;
    }

    public boolean isIsEms() {
        return isEms;
    }
}
