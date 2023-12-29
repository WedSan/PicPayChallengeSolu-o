package com.desafiopicpayandrelson.desafiopicpay.model;

import com.desafiopicpayandrelson.desafiopicpay.model.User.UserEntity;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb_transaction")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal amount;

    @ManyToOne
    private UserEntity sender;

    @ManyToOne
    private UserEntity receiver;

    private LocalDateTime timestamp;
}
