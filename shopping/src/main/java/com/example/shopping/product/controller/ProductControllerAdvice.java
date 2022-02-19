package com.example.shopping.product.controller;

import com.example.shopping.product.exception.ExceptionModel;
import com.example.shopping.product.exception.ProductNotFoundException;
import com.example.shopping.product.model.DetailResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@RestControllerAdvice
public class ProductControllerAdvice {

    @ExceptionHandler(ProductNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionModel productNotFound(ProductNotFoundException e, HttpServletRequest req) {
        ExceptionModel exceptionData = new ExceptionModel();
        exceptionData.setStatus(HttpStatus.NOT_FOUND.value());
        exceptionData.setError(HttpStatus.NOT_FOUND.name());
        exceptionData.setMessage(e.getMessage());
        String path = req.getRequestURI();
        exceptionData.setPath(path);
        exceptionData.setTimestamp(new Date());
        return exceptionData;
    }
}
