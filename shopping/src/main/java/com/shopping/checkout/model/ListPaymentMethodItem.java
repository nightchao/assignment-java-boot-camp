package com.shopping.checkout.model;

public class ListPaymentMethodItem {

    private int paymentMethodId;
    private String name;

    public ListPaymentMethodItem() {

    }

    public int getPaymentMethodId() {
        return paymentMethodId;
    }

    public void setPaymentMethodId(int paymentMethodId) {
        this.paymentMethodId = paymentMethodId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
