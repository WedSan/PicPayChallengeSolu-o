package com.desafiopicpayandrelson.desafiopicpay.exceptionHandler;

import com.desafiopicpayandrelson.desafiopicpay.exception.AuthorizationException;
import com.desafiopicpayandrelson.desafiopicpay.exception.AuthorizationResponseException;
import com.desafiopicpayandrelson.desafiopicpay.exception.InvalidAuthorizationResponseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AuthorizationExceptionHandler {

    @ExceptionHandler(AuthorizationResponseException.class)
    public ResponseEntity authorizationResponseException(AuthorizationResponseException ex){
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ex.getMessage());
    }

    @ExceptionHandler(InvalidAuthorizationResponseException.class)
    public ResponseEntity invalidAuthorizationResponseException(InvalidAuthorizationResponseException ex){
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ex.getMessage());
    }

    @ExceptionHandler(AuthorizationException.class)
    public ResponseEntity authorizationException(AuthorizationException ex){
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ex.getMessage());
    }

}
