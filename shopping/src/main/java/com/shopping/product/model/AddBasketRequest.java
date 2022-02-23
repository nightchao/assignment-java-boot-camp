package com.shopping.product.model;

import javax.validation.constraints.NotNull;

public class AddBasketRequest {

    @NotNull(message = "userId is null")
    private Integer userId;

    @NotNull(message = "productId is null")
    private Integer productId;

    @NotNull(message = "quantity is null")
    private Integer quantity;

    private String image;
    private Integer size;

    public AddBasketRequest() {

    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
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
