package com.desafiopicpayandrelson.desafiopicpay.exceptionHandler;

import com.desafiopicpayandrelson.desafiopicpay.exception.InsufficientFundsException;
import com.desafiopicpayandrelson.desafiopicpay.exception.TransactionNotAuthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TransactionExcpetionHandler {

    @ExceptionHandler(InsufficientFundsException.class)
    public ResponseEntity InsufficientFundsException(InsufficientFundsException ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(TransactionNotAuthorizedException.class)
    public ResponseEntity TransactionNotAuthorizedException(TransactionNotAuthorizedException ex){
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ex.getMessage());
    }

}
