package com.shopping.checkout.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.user.entity.Address;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserAddress extends Address {

    private String fullName;
    private String email;

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
