package com.shopping.product.db;

import java.io.Serializable;

public class OrderListId implements Serializable {

    private String orderId;
    private int userId;

    public OrderListId() {

    }

    public OrderListId(String orderId, int userId) {
        this.orderId = orderId;
        this.userId = userId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
