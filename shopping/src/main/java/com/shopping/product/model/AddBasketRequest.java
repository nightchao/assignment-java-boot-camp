package com.shopping.product.model;

import javax.validation.constraints.NotNull;

public class AddBasketRequest {

    @NotNull(message = "userId is null")
    private int userId;

    @NotNull(message = "productId is null")
    private Integer productId;

    @NotNull(message = "quantity is null")
    private Integer quantity;

    private String image;
    private Integer size;

    public AddBasketRequest(int userId, Integer productId, Integer quantity) {
        this.userId = userId;
        this.productId = productId;
        this.quantity = quantity;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }
}
