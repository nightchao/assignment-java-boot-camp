package com.shopping.checkout.db;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "SUMMARY")
public class Summary {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer invoiceNo;

    private String orderId;
    private String payer;

    @Temporal(TemporalType.TIMESTAMP)
    private Date transactionDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date expiredDate;

    private String payee;
    private String detail;
    private int amount;
    private int paymentMethodId;
    private Boolean isReceiptVat;
    private Boolean isGetNews;

    public Summary() {

    }

    public Integer getInvoiceNo() {
        return invoiceNo;
    }

    public void setInvoiceNo(Integer invoiceNo) {
        this.invoiceNo = invoiceNo;
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

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public Date getExpiredDate() {
        return expiredDate;
    }

    public void setExpiredDate(Date expiredDate) {
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
