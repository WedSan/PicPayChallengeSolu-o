package com.desafiopicpayandrelson.desafiopicpay.exception;

public class InvalidAuthorizationResponseException extends AuthorizationException {
    public InvalidAuthorizationResponseException(String message) {
        super(message);
    }
}
