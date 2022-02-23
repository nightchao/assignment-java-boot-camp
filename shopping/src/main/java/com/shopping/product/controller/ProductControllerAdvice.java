package com.shopping.product.controller;

import com.exception.BasketItemNotFoundException;
import com.exception.ExceptionModel;
import com.exception.ProductNotFoundException;
import com.exception.SearchNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@RestControllerAdvice
public class ProductControllerAdvice {

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

    @ExceptionHandler(SearchNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionModel searchNotFound(SearchNotFoundException e, HttpServletRequest req) {
        return getObjectMsg(e.getMessage(), req);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionModel productNotFound(ProductNotFoundException e, HttpServletRequest req) {
        return getObjectMsg(e.getMessage(), req);
    }

    @ExceptionHandler(BasketItemNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionModel basketItemNotFound(BasketItemNotFoundException e, HttpServletRequest req) {
        return getObjectMsg(e.getMessage(), req);
    }
}
