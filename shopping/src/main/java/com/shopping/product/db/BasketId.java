package com.shopping.product.db;

import java.io.Serializable;

public class BasketId implements Serializable {

    private int userId;
    private int productId;

    public BasketId() {

    }

    public BasketId(int userId, int productId) {
        this.userId = userId;
        this.productId = productId;
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
}
