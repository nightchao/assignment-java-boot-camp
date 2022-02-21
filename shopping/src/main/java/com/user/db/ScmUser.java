package com.user.db;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "USER")
public class ScmUser {

    @Id
    private int userId;

    private String fullName;
    private String email;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
