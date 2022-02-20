package com.example.shopping.product.db;

import javax.persistence.*;

@Entity(name = "ORDER_LIST")
@IdClass(OrderListId.class)
public class OrderList {

    @Id
    private String orderId;

    @Id
    private int userId;

    private int productId;
    private int quantity;
    private int price;
    private Integer vat;

    public OrderList() {

    }

    public OrderList(String orderId, int userId, int productId, int quantity, int price, Integer vat) {
        this.orderId = orderId;
        this.userId = userId;
        this.productId = productId;
        this.quantity = quantity;
        this.price = price;
        this.vat = vat;
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

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Integer getVat() {
        return vat;
    }

    public void setVat(Integer vat) {
        this.vat = vat;
    }
}
