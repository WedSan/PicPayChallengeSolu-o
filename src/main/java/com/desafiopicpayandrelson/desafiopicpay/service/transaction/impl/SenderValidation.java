package com.desafiopicpayandrelson.desafiopicpay.service.transaction.impl;

import com.desafiopicpayandrelson.desafiopicpay.exception.InsufficientFundsException;
import com.desafiopicpayandrelson.desafiopicpay.model.User.UserEntity;
import com.desafiopicpayandrelson.desafiopicpay.service.transaction.TransactionValidator;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class SenderValidation implements TransactionValidator {

    public SenderValidation() {
    }

    @Override
    public void validate(UserEntity sender, UserEntity receiver, BigDecimal amount) {
        validateSenderAccountType(sender);
        validateSufficientFunds(sender, amount);
    }

    private void validateSenderAccountType(UserEntity sender){
        if(sender.getAccountType().toString() != "USER"){
            throw new IllegalArgumentException("Sender must have USER account type");
        }
    }

    private void validateSufficientFunds(UserEntity sender, BigDecimal amount){
        if(sender.getBalance().compareTo(amount)<0){
            throw new InsufficientFundsException("Sender does not have sufficient funds for the transaction");
        }
    }

}
