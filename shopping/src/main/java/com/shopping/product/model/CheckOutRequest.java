package com.shopping.product.model;

import javax.validation.constraints.NotNull;

public class CheckOutRequest {

    @NotNull(message = "userId is null")
    private Integer userId;

    public CheckOutRequest() {

    }

    public CheckOutRequest(Integer userId) {
        this.userId = userId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
