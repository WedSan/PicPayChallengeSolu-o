package com.desafiopicpayandrelson.desafiopicpay.service.transaction;

import com.desafiopicpayandrelson.desafiopicpay.dto.TransactionDto;
import com.desafiopicpayandrelson.desafiopicpay.model.User.UserEntity;

import java.math.BigDecimal;

public interface TransactionValidator {

    void validate(UserEntity sender, UserEntity receiver, BigDecimal amount);

}
