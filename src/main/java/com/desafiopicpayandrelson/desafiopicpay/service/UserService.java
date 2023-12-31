package com.desafiopicpayandrelson.desafiopicpay.service;

import com.desafiopicpayandrelson.desafiopicpay.dto.UserDto;
import com.desafiopicpayandrelson.desafiopicpay.model.User.UserEntity;

public interface UserService {

    UserEntity create(UserDto userDto);

    UserEntity findUser(Long id);

    UserEntity save(UserEntity userEntity);
}
