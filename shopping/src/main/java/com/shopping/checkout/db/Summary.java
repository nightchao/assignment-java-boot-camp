package com.shopping.checkout.db;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "SUMMARY")
public class Summary {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer summaryId;

    private String orderId;
    private String payer;
    private String transactionDate;
    private String expiredDate;
    private String payee;
    private String detail;
    private int amount;
    private int paymentMethodId;
    private Boolean isReceiptVat;
    private Boolean isGetNews;

    public Summary() {

    }

    public Integer getSummaryId() {
        return summaryId;
    }

    public void setSummaryId(Integer summaryId) {
        this.summaryId = summaryId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getPayer() {
        return payer;
    }

    public void setPayer(String payer) {
        this.payer = payer;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getExpiredDate() {
        return expiredDate;
    }

    public void setExpiredDate(String expiredDate) {
        this.expiredDate = expiredDate;
    }

    public String getPayee() {
        return payee;
    }

    public void setPayee(String payee) {
        this.payee = payee;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getPaymentMethodId() {
        return paymentMethodId;
    }

    public void setPaymentMethodId(int paymentMethodId) {
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

    public void setIsGetNews(Boolean isGetNews) {
        this.isGetNews = isGetNews;
    }
}
