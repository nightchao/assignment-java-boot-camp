package com.shopping.checkout.model;

import javax.validation.constraints.NotNull;

public class ConfirmOrderRequest {

    @NotNull(message = "orderId is null")
    private String orderId;

    @NotNull(message = "paymentMethodId is null")
    private Integer paymentMethodId;

    private Boolean isReceiptVat;

    private Boolean isGetNews;

    public ConfirmOrderRequest() {

    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Integer getPaymentMethodId() {
        return paymentMethodId;
    }

    public void setPaymentMethodId(Integer paymentMethodId) {
        this.paymentMethodId = paymentMethodId;
    }

    public Boolean getIsReceiptVat() {
        return isReceiptVat;
    }

    public void setIsReceiptVat(Boolean isReceiptVat) {
        this.isReceiptVat = isReceiptVat;
    }

    public Boolean getIsGetNews() {
        return isGetNews;
    }

    public void setIsGetNews(Boolean isGetNew) {
        this.isGetNews = isGetNew;
    }
}
