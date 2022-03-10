package com.user.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

@Entity(name = "ADDRESS")
@IdClass(AddressId.class)
public class Address {

    @Id
    private int addressId;

    @Id
    @Column(name = "USER_ID")
    private Integer userId;

    private String address;
    private String district;
    private String province;
    private String telephone;

    @Column(name = "IS_DEFAULT")
    private Integer isDefault;

    public Address() {

    }

    public Address(int addressId, Integer userId) {
        this.addressId = addressId;
        this.userId = userId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public int isDefault() {
        return isDefault;
    }

    public void setDefault(int isDefault) {
        this.isDefault = isDefault;
    }
}
