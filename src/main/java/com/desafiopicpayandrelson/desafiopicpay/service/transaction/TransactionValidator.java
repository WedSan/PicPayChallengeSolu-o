package com.desafiopicpayandrelson.desafiopicpay.service.transaction;

import com.desafiopicpayandrelson.desafiopicpay.dto.TransactionDto;

public interface TransactionValidator {

    void validate(TransactionDto transactionDto);

}
