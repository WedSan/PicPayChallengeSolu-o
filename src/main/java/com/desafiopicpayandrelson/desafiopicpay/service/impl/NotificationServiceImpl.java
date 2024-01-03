package com.desafiopicpayandrelson.desafiopicpay.service.impl;

import com.desafiopicpayandrelson.desafiopicpay.dto.NotificationDto;
import com.desafiopicpayandrelson.desafiopicpay.service.NotificationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class NotificationServiceImpl implements NotificationService {

    @Override
    public void sendNotification(String email, String message) {
        NotificationDto notificationRequest = new NotificationDto(email, message);
        ResponseEntity notificationResponse = sendNotificationRequest(notificationRequest);
        System.out.println(notificationResponse);
        if (!(notificationResponse.getStatusCode() == HttpStatus.OK)) {
            throw new RuntimeException("Notificaiton Service is offline");
        }
    }

    private ResponseEntity sendNotificationRequest(NotificationDto notificationRequest){
        WebClient webClient =  WebClient.create("https://run.mocky.io/v3/54dc2cf1-3add-45b5-b5a9-6bf7e7f1f4a6");
        return webClient.post()
                .bodyValue(notificationRequest)
                .retrieve()
                .bodyToMono(ResponseEntity.class)
                .block();
    }



}
