package com.shopping.checkout.db;

import javax.persistence.*;

@Entity(name = "ORDER_LIST")
public class OrderList {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    private String orderId;
    private int userId;
    private int productId;
    private int quantity;
    private int price;
    private Integer vat;
    private boolean isEms;

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public boolean isEms() {
        return isEms;
    }

    public void setEms(boolean ems) {
        isEms = ems;
    }
}
