package com.desafiopicpayandrelson.desafiopicpay.service;

import com.desafiopicpayandrelson.desafiopicpay.dto.TransactionDto;
import com.desafiopicpayandrelson.desafiopicpay.model.Transaction;

public interface TransactionService {

    Transaction createTransaction(TransactionDto transactionDto);
}
