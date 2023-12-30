package com.desafiopicpayandrelson.desafiopicpay.service.impl;

import com.desafiopicpayandrelson.desafiopicpay.dto.UserDto;
import com.desafiopicpayandrelson.desafiopicpay.model.User.UserEntity;
import com.desafiopicpayandrelson.desafiopicpay.repository.UserEntityRepository;
import com.desafiopicpayandrelson.desafiopicpay.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private UserEntityRepository userRepository;


    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserEntity create(UserDto userDto) {
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(userDto.email());
        userEntity.setBalance(userDto.balance());
        String passwordEncoded = this.passwordEncoder.encode(userDto.password());
        userEntity.setPassword(passwordEncoded);
        userEntity.setAccountType(userDto.accountType());
        return userEntity;
    }


    @Override
    public UserEntity save(UserEntity userEntity) {
        return userRepository.save(userEntity);
    }
}
