package com.desafiopicpayandrelson.desafiopicpay.service.impl;

import com.desafiopicpayandrelson.desafiopicpay.dto.TransactionDto;
import com.desafiopicpayandrelson.desafiopicpay.exception.AuthorizationException;
import com.desafiopicpayandrelson.desafiopicpay.exception.InvalidAuthorizationResponseException;
import com.desafiopicpayandrelson.desafiopicpay.exception.TransactionNotAuthorizedException;
import com.desafiopicpayandrelson.desafiopicpay.model.Transaction;
import com.desafiopicpayandrelson.desafiopicpay.model.User.UserEntity;
import com.desafiopicpayandrelson.desafiopicpay.model.response.AuthorizationResponse;
import com.desafiopicpayandrelson.desafiopicpay.repository.TransactionRepository;
import com.desafiopicpayandrelson.desafiopicpay.service.TransactionService;
import com.desafiopicpayandrelson.desafiopicpay.service.UserService;
import com.desafiopicpayandrelson.desafiopicpay.service.transaction.TransactionValidator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.Disposable;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.List;

@Service
public class TransectionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;

    private final UserService userService;

    private final List<TransactionValidator> transactionValidators;

    private final AuthorizationService authorizationService;

    @Autowired
    public TransectionServiceImpl(TransactionRepository transactionRepository, UserService userService, List<TransactionValidator> transactionValidators, AuthorizationService authorizationService) {
        this.transactionRepository = transactionRepository;
        this.userService = userService;
        this.transactionValidators = transactionValidators;
        this.authorizationService = authorizationService;
    }

    @Override
    public Transaction createTransaction(TransactionDto transactionDto) {
        UserEntity receiver = this.userService.findUser(transactionDto.sender());
        UserEntity sender = this.userService.findUser(transactionDto.receiver());
        BigDecimal transactionValue = transactionDto.value();

        validateTransaction(receiver, sender , transactionValue);
        authorizationService.authorizeTransaction(receiver, sender, transactionValue);

        Transaction transaction = new Transaction(receiver, sender, transactionValue);
        return this.transactionRepository.save(transaction);
    }

    private void validateTransaction(UserEntity receiver, UserEntity sender, BigDecimal transactionValue){
        this.transactionValidators.forEach(tValidation -> tValidation.validate(receiver, sender, transactionValue));
    }



}
