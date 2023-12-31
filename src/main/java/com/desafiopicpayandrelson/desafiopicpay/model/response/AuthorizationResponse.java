package com.desafiopicpayandrelson.desafiopicpay.model.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record AuthorizationResponse(
        String message
) {
}
