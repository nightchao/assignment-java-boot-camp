package com.user.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.user.db.Address;

public class AddressResponse extends Address {

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
