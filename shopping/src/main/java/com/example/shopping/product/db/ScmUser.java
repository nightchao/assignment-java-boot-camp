package com.example.shopping.product.db;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "USER")
public class ScmUser {

    @Id
    private int userId;

    private String fullName;

    public ScmUser() {

    }

    public ScmUser(int userId, String fullName) {
        this.userId = userId;
        this.fullName = fullName;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
