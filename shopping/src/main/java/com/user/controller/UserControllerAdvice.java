package com.user.controller;

import com.exception.AddressNotFoundException;
import com.exception.ExceptionModel;
import com.exception.ProductNotFoundException;
import com.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@RestControllerAdvice
public class UserControllerAdvice {

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

    @ExceptionHandler(AddressNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionModel addressNotFound(ProductNotFoundException e, HttpServletRequest req) {
        return getObjectMsg(e.getMessage(), req);
    }

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionModel userNotFound(UserNotFoundException e, HttpServletRequest req) {
        return getObjectMsg(e.getMessage(), req);
    }
}
