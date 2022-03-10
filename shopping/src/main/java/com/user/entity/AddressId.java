package com.user.entity;

import java.io.Serializable;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddressId addressId1 = (AddressId) o;
        return addressId == addressId1.addressId && userId == addressId1.userId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(addressId, userId);
    }
}
