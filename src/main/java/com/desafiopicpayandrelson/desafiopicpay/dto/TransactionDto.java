package com.desafiopicpayandrelson.desafiopicpay.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.NumberFormat;

import java.math.BigDecimal;

public record TransactionDto(
        @DecimalMin(value = "0.01", inclusive = true, message = "O valor mínimo deve ser de 0.01")
        BigDecimal value,
        @NotNull
        Long sender,
        @NotNull
        Long receiver

) {
}
