package com.desafiopicpayandrelson.desafiopicpay.dto;

import com.desafiopicpayandrelson.desafiopicpay.model.User.AccountType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.math.BigDecimal;

public record UserDto(
    @NotBlank
    String name,

    @Email
    String email,

    @NotBlank
    String password,

    @NotBlank
    String document,

    AccountType accountType,

    BigDecimal balance
) {



}
