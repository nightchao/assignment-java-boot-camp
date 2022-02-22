package com.shopping.checkout.model;

import java.util.List;

public class ShippingResponse {

    private String orderId;
    private Boolean isEms;
    private String deliveryTime;
    private int total;
    private List<ListShoppingItem> listShopping;
    private UserAddress userAddress;

    public ShippingResponse() {

    }

    public ShippingResponse(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public Boolean getIsEms() {
        return isEms;
    }

    public void setIsEms(Boolean isEms) {
        this.isEms = isEms;
    }

    public String getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(String deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public List<ListShoppingItem> getListShopping() {
        return listShopping;
    }

    public void setListShopping(List<ListShoppingItem> listShopping) {
        this.listShopping = listShopping;
    }

    public UserAddress getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(UserAddress userAddress) {
        this.userAddress = userAddress;
    }
}
