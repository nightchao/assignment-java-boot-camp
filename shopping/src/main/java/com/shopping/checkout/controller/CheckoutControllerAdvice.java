package com.shopping.checkout.controller;

import com.exception.*;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
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
public class CheckoutControllerAdvice {

    private ExceptionModel getObjectMsg(String msg, HttpServletRequest req, HttpStatus httpStatus) {
        ExceptionModel exceptionData = new ExceptionModel();
        exceptionData.setStatus(httpStatus.value());
        exceptionData.setError(httpStatus.name());
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
        return getObjectMsg(e.getMessage(), req, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AddressNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionModel addressNotFound(AddressNotFoundException e, HttpServletRequest req) {
        return getObjectMsg(e.getMessage(), req, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionModel userNotFound(UserNotFoundException e, HttpServletRequest req) {
        return getObjectMsg(e.getMessage(), req, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PaymentNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionModel paymentNotFound(PaymentNotFoundException e, HttpServletRequest req) {
        return getObjectMsg(e.getMessage(), req, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionModel handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpServletRequest req) {

        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());

        return getObjectMsg(String.join(",", errors), req, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(SummaryNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionModel summaryNotFound(SummaryNotFoundException e, HttpServletRequest req) {
        return getObjectMsg(e.getMessage(), req, HttpStatus.NOT_FOUND);
    }
}
