package com.desafiopicpayandrelson.desafiopicpay.exception;

public class TransactionNotAuthorizedException extends RuntimeException {

    public TransactionNotAuthorizedException(String message) {
        super(message);
    }

    public TransactionNotAuthorizedException(String message, Throwable cause) {
        super(message, cause);
    }
}
