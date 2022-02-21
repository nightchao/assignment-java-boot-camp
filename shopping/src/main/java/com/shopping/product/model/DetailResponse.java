package com.shopping.product.model;

import com.shopping.product.db.Product;

import java.util.List;

public class DetailResponse extends Product {

    private List<Integer> size;
    private String dateExpiredPromotion;
    private List<String> listImg;

    public DetailResponse() {
    }

    public DetailResponse(List<Integer> size, List<String> listImg) {
        this.size = size;
        this.listImg = listImg;
    }

    public List<Integer> getSize() {
        return size;
    }

    public void setSize(List<Integer> size) {
        this.size = size;
    }

    public String getDateExpiredPromotion() {
        return dateExpiredPromotion;
    }

    public void setDateExpiredPromotion(String dateExpiredPromotion) {
        this.dateExpiredPromotion = dateExpiredPromotion;
    }

    public List<String> getListImg() {
        return listImg;
    }

    public void setListImg(List<String> listImg) {
        this.listImg = listImg;
    }
}