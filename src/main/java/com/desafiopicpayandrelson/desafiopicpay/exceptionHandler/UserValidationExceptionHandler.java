package com.desafiopicpayandrelson.desafiopicpay.exceptionHandler;

import com.desafiopicpayandrelson.desafiopicpay.exception.DuplicateUserDocumentException;
import com.desafiopicpayandrelson.desafiopicpay.exception.DuplicateUserEmailException;
import com.desafiopicpayandrelson.desafiopicpay.exception.UserIdNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class UserValidationExceptionHandler {


    @ExceptionHandler(UserIdNotFoundException.class)
    public ResponseEntity userIdNotFoundException(UserIdNotFoundException ex){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }


    @ExceptionHandler(DuplicateUserEmailException.class)
    public ResponseEntity duplicateUserrEmailException(DuplicateUserEmailException ex){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }

    @ExceptionHandler(DuplicateUserDocumentException.class)
    public ResponseEntity duplicateUserDocumentException(DuplicateUserDocumentException ex){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }
}
