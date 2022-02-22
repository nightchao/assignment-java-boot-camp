package com.shopping.checkout.model;

public class ConfirmOrderResponse {

    private Integer invoiceNo;
    private String message;

    public ConfirmOrderResponse() {

    }

    public ConfirmOrderResponse(Integer invoiceNo, String message) {
        this.invoiceNo = invoiceNo;
        this.message = message;
    }

    public Integer getInvoiceNo() {
        return invoiceNo;
    }

    public void setInvoiceNo(Integer invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
