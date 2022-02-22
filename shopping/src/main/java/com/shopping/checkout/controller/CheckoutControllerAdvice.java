package com.shopping.checkout.controller;

import com.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@RestControllerAdvice
public class CheckoutControllerAdvice {

    private ExceptionModel getObjectMsg(String msg, HttpServletRequest req) {
        ExceptionModel exceptionData = new ExceptionModel();
        exceptionData.setStatus(HttpStatus.NOT_FOUND.value());
        exceptionData.setError(HttpStatus.NOT_FOUND.name());
        exceptionData.setMessage(msg);
        String path = req.getRequestURI();
        exceptionData.setPath(path);
        exceptionData.setTimestamp(new Date());
        return exceptionData;
    }

    @ExceptionHandler(OrderNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionModel orderNotFound(OrderNotFoundException e, HttpServletRequest req) {
        return getObjectMsg(e.getMessage(), req);
    }

    @ExceptionHandler(AddressNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionModel addressNotFound(AddressNotFoundException e, HttpServletRequest req) {
        return getObjectMsg(e.getMessage(), req);
    }

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionModel userNotFound(UserNotFoundException e, HttpServletRequest req) {
        return getObjectMsg(e.getMessage(), req);
    }

    @ExceptionHandler(PaymentNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionModel userNotFound(PaymentNotFoundException e, HttpServletRequest req) {
        return getObjectMsg(e.getMessage(), req);
    }
}
