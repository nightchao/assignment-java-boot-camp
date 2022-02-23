package com.exception;

public class SummaryNotFoundException extends RuntimeException {

    public SummaryNotFoundException(int invoiceNo) {
        super("Summary: " + invoiceNo + " not found");
    }
}
