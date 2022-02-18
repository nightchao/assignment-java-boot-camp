package com.example.shopping.product.db;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity(name = "PRODUCT")
public class Product {

    @Id
    private int productId;

    private int quantity;
    private int categoryId;
    private String name;
    private String description;
    private int price;
    private int discount; // percent
    private String brand;
    private String brandUrl;
    private String note;
    private String noteUrl;
    private int warranty; // day
    private Date dateExpiredPromotion;
    private double rating;
    private int ratingVote;
    private String province;
    private boolean isEms;

    public Product() {
    }

    public Product(int productId, String name) {
        this.productId = productId;
        this.name = name;
    }

    public Product(int productId, int quantity, int categoryId, int price, int discount, int warranty, boolean isEms) {
        this.productId = productId;
        this.quantity = quantity;
        this.categoryId = categoryId;
        this.price = price;
        this.discount = discount;
        this.warranty = warranty;
        this.isEms = isEms;
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

    public int getWarranty() {
        return warranty;
    }

    public void setWarranty(int warranty) {
        this.warranty = warranty;
    }

    public Date getDateExpiredPromotion() {
        return dateExpiredPromotion;
    }

    public void setDateExpiredPromotion(Date dateExpiredPromotion) {
        this.dateExpiredPromotion = dateExpiredPromotion;
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

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public boolean isEms() {
        return isEms;
    }

    public void setEms(boolean ems) {
        isEms = ems;
    }
}
