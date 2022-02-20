package com.example.shopping.product.model;

import com.example.shopping.product.db.Product;

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
