package com.desafiopicpayandrelson.desafiopicpay.controller;

import com.desafiopicpayandrelson.desafiopicpay.dto.TransactionDto;
import com.desafiopicpayandrelson.desafiopicpay.dto.TransactionResponseDto;
import com.desafiopicpayandrelson.desafiopicpay.model.Transaction;
import com.desafiopicpayandrelson.desafiopicpay.service.TransactionService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/transaction")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("send")
    public ResponseEntity transferFunds(@RequestBody @Valid TransactionDto transactionDto){
        Transaction transaction = this.transactionService.createTransaction(transactionDto);
        TransactionResponseDto transactionResponse = new TransactionResponseDto(transaction);
        return ResponseEntity.status(HttpStatus.CREATED).body(transactionResponse);
    }

}
