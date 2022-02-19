package com.example.shopping.product.controller;

import com.example.shopping.product.exception.ExceptionModel;
import com.example.shopping.product.exception.ProductNotFoundException;
import com.example.shopping.product.exception.UserNotFoundException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionModel handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpServletRequest req
    ) {

        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());

        ExceptionModel exceptionData = new ExceptionModel();
        exceptionData.setStatus(HttpStatus.BAD_REQUEST.value());
        exceptionData.setError(HttpStatus.BAD_REQUEST.name());
        exceptionData.setMessage(String.join(",", errors));
        String path = req.getRequestURI();
        exceptionData.setPath(path);
        exceptionData.setTimestamp(new Date());
        return exceptionData;
    }

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionModel productNotFound(UserNotFoundException e, HttpServletRequest req) {
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
