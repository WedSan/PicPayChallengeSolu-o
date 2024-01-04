package com.desafiopicpayandrelson.desafiopicpay.service.impl;

import com.desafiopicpayandrelson.desafiopicpay.dto.TransactionDto;
import com.desafiopicpayandrelson.desafiopicpay.model.Transaction;
import com.desafiopicpayandrelson.desafiopicpay.model.User.UserEntity;
import com.desafiopicpayandrelson.desafiopicpay.repository.TransactionRepository;
import com.desafiopicpayandrelson.desafiopicpay.service.NotificationService;
import com.desafiopicpayandrelson.desafiopicpay.service.TransactionService;
import com.desafiopicpayandrelson.desafiopicpay.service.UserService;
import com.desafiopicpayandrelson.desafiopicpay.service.transaction.TransactionValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class TransectionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;

    private final UserService userService;

    private final List<TransactionValidator> transactionValidators;

    private final AuthorizationService authorizationService;

    private final NotificationService notificationService;



    @Autowired
    public TransectionServiceImpl(TransactionRepository transactionRepository, UserService userService, List<TransactionValidator> transactionValidators, AuthorizationService authorizationService, NotificationService notificationService) {
        this.transactionRepository = transactionRepository;
        this.userService = userService;
        this.transactionValidators = transactionValidators;
        this.authorizationService = authorizationService;
        this.notificationService = notificationService;
    }

    @Override
    public Transaction createTransaction(TransactionDto transactionDto) {
        UserEntity receiver = this.userService.findUser(transactionDto.sender());
        UserEntity sender = this.userService.findUser(transactionDto.receiver());

        BigDecimal transactionValue = transactionDto.value();

        validateTransaction(receiver, sender, transactionValue);
        authorizationService.authorizeTransaction(receiver, sender, transactionValue);

        Transaction transaction = new Transaction(receiver, sender, transactionValue);
        transferBalance(sender, receiver, transactionValue);

        notificationService.sendNotification(sender.getEmail(), "Transaction completed successfully");
        notificationService.sendNotification(receiver.getEmail(), "Transaction completed successfully");

        return this.transactionRepository.save(transaction);
    }

    private void validateTransaction(UserEntity receiver, UserEntity sender, BigDecimal transactionValue){
        this.transactionValidators.forEach(tValidation -> tValidation.validate(receiver, sender, transactionValue));
    }

    private void transferBalance(UserEntity sender, UserEntity receiver, BigDecimal value){
        sender.setBalance(sender.getBalance().subtract(value));
        receiver.setBalance(receiver.getBalance().add(value));
        userService.save(sender);
        userService.save(receiver);
    }


}
