package com.desafiopicpayandrelson.desafiopicpay.repository;

import com.desafiopicpayandrelson.desafiopicpay.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
