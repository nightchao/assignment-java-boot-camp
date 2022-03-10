package com.shopping.product.entity;

import java.io.Serializable;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BasketId basketId = (BasketId) o;
        return userId == basketId.userId && productId == basketId.productId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, productId);
    }
}
