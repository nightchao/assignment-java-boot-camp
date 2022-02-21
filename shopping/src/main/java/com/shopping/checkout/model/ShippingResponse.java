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

    public boolean isEms() {
        return isEms;
    }

    public void setEms(boolean ems) {
        isEms = ems;
    }

    public String getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(String deliveryTime) {
        this.deliveryTime = deliveryTime;
    }
}
