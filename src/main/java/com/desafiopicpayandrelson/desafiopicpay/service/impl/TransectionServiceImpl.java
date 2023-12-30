package com.desafiopicpayandrelson.desafiopicpay.service.impl;

import com.desafiopicpayandrelson.desafiopicpay.dto.TransactionDto;
import com.desafiopicpayandrelson.desafiopicpay.model.Transaction;
import com.desafiopicpayandrelson.desafiopicpay.repository.TransactionRepository;
import com.desafiopicpayandrelson.desafiopicpay.repository.UserEntityRepository;
import com.desafiopicpayandrelson.desafiopicpay.service.TransactionService;
import com.desafiopicpayandrelson.desafiopicpay.service.transaction.TransactionValidator;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransectionServiceImpl implements TransactionService {

    private TransactionRepository transactionRepository;

    private UserEntityRepository userEntityRepository;

    private List<TransactionValidator> transactionValidators;

    @Override
    public Transaction createTransaction(TransactionDto transactionDto) {

        transactionValidators.forEach(tValidator -> tValidator.validate(transactionDto));


    }
}
