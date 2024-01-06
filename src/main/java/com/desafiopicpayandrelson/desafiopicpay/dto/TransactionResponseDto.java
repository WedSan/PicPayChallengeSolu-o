package com.desafiopicpayandrelson.desafiopicpay.dto;

import com.desafiopicpayandrelson.desafiopicpay.model.Transaction;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record TransactionResponseDto (
    Long id,
    BigDecimal value,

    Long senderId,
    Long receiverId,

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy'T'HH:mm:ss")
    LocalDateTime localDateTime

){
    public TransactionResponseDto(Transaction transaction){
        this(transaction.getId(),
                transaction.getAmount(),
                transaction.getSender().getId(),
                transaction.getReceiver().getId(),
                transaction.getTimestamp());
    }
}
