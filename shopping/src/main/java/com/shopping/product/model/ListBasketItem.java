package com.shopping.product.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.shopping.product.entity.Product;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ListBasketItem extends Product {

    private Integer size;
    private String image;

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
