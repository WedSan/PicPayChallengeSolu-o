package com.desafiopicpayandrelson.desafiopicpay.dto;

import com.desafiopicpayandrelson.desafiopicpay.model.User.AccountType;
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

    String document,

    AccountType accountType,

    BigDecimal balance
) {



}
