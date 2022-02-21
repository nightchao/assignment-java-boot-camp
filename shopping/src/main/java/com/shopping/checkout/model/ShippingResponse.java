package com.shopping.checkout.model;

public class ShippingResponse {

    private Boolean isEms;
    private String deliveryTime;

    public ShippingResponse() {

    }

    public ShippingResponse(Boolean isEms, String deliveryTime) {
        this.isEms = isEms;
        this.deliveryTime = deliveryTime;
    }

    public Boolean getIsEms() {
        return isEms;
    }

    public void setIdEms(Boolean isEms) {
        this.isEms = isEms;
    }

    public String getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(String deliveryTime) {
        this.deliveryTime = deliveryTime;
    }
}
