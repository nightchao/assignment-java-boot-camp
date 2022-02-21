package com.user.db;

import java.io.Serializable;

public class AddressId implements Serializable {

    private int addressId;
    private int userId;

    public AddressId() {

    }

    public AddressId(int addressId, int userId) {
        this.addressId = addressId;
        this.userId = userId;
    }

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
