package com.desafiopicpayandrelson.desafiopicpay.service;

import com.desafiopicpayandrelson.desafiopicpay.model.Transaction;
import com.desafiopicpayandrelson.desafiopicpay.model.User.UserEntity;

public interface NotificationService {

    /**
     * @param user
     */
    void sendNotification(String email, String message);
}
