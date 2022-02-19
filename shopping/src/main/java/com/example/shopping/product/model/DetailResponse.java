package com.example.shopping.product.model;

import java.util.List;

public class DetailResponse {

    private int productId;
    private int quantity;
    private int categoryId;
    private String name;
    private String description;
    private int price;
    private int discount;
    private List<Integer> size;
    private String brand;
    private String brandUrl;
    private String note;
    private String noteUrl;
    private double rating;
    private int ratingVote;
    private int warranty;
    private String dateExpiredPromotion;
    private List<String> listImg;

    public DetailResponse() {
    }

    public DetailResponse(List<Integer> size, List<String> listImg) {
        this.size = size;
        this.listImg = listImg;
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

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public List<Integer> getSize() {
        return size;
    }

    public void setSize(List<Integer> size) {
        this.size = size;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getBrandUrl() {
        return brandUrl;
    }

    public void setBrandUrl(String brandUrl) {
        this.brandUrl = brandUrl;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getNoteUrl() {
        return noteUrl;
    }

    public void setNoteUrl(String noteUrl) {
        this.noteUrl = noteUrl;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getRatingVote() {
        return ratingVote;
    }

    public void setRatingVote(int ratingVote) {
        this.ratingVote = ratingVote;
    }

    public int getWarranty() {
        return warranty;
    }

    public void setWarranty(int warranty) {
        this.warranty = warranty;
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