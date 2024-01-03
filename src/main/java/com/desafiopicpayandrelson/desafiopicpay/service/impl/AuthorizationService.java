package com.desafiopicpayandrelson.desafiopicpay.service.impl;

import com.desafiopicpayandrelson.desafiopicpay.exception.InvalidAuthorizationResponseException;
import com.desafiopicpayandrelson.desafiopicpay.exception.TransactionNotAuthorizedException;
import com.desafiopicpayandrelson.desafiopicpay.model.User.UserEntity;
import com.desafiopicpayandrelson.desafiopicpay.model.response.AuthorizationResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

@Service
public class AuthorizationService {

    public AuthorizationService() {
    }

    /*
    * This method SIMULATES an authorization from an external service, as requested in the challenge
    */
    public void authorizeTransaction(UserEntity receiver, UserEntity sender, BigDecimal amount){
        String json = requestAuthorization();
        var authorizationResponse = extractAuthorizationResponse(json);

        if(!(authorizationResponse.message().equals("Autorizado"))){
            throw new TransactionNotAuthorizedException("Transaction not authorized");
        }
    }

    private AuthorizationResponse extractAuthorizationResponse(String result){
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(result, AuthorizationResponse.class);
        }
        catch(JsonProcessingException ex){
            throw new InvalidAuthorizationResponseException("Error parsing authorization response\", ex");
        }
    }

    private String requestAuthorization(){
        WebClient webClient = WebClient.create("https://run.mocky.io/v3/5794d450-d2e2-4412-8131-73d0293ac1cc");
        Mono<String> responseBody = webClient.get().retrieve().bodyToMono(String.class);
        return responseBody.block();
    }

}
