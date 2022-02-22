package com.shopping.checkout.model;

import java.util.List;

public class PaymentMethodResponse {

    private List<ListPaymentMethodItem> listPaymentMethod;

    public PaymentMethodResponse() {

    }

    public PaymentMethodResponse(List<ListPaymentMethodItem> listPaymentMethod) {
        this.listPaymentMethod = listPaymentMethod;
    }

    public List<ListPaymentMethodItem> getListPaymentMethod() {
        return listPaymentMethod;
    }

    public void setListPaymentMethod(List<ListPaymentMethodItem> listPaymentMethod) {
        this.listPaymentMethod = listPaymentMethod;
    }
}