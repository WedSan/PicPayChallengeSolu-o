package com.desafiopicpayandrelson.desafiopicpay.service.impl;

import com.desafiopicpayandrelson.desafiopicpay.dto.UserDto;
import com.desafiopicpayandrelson.desafiopicpay.exception.UserIdNotFoundException;
import com.desafiopicpayandrelson.desafiopicpay.model.User.UserEntity;
import com.desafiopicpayandrelson.desafiopicpay.repository.UserEntityRepository;
import com.desafiopicpayandrelson.desafiopicpay.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.xml.transform.stream.StreamSource;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserEntityRepository userRepository;


    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserEntityRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserEntity create(UserDto userDto) {
        UserEntity userEntity = new UserEntity();
        userEntity.setName(userDto.name());
        userEntity.setDocument(userDto.document());
        userEntity.setEmail(userDto.email());
        userEntity.setBalance(userDto.balance());
        String passwordEncoded = this.passwordEncoder.encode(userDto.password());
        userEntity.setPassword(passwordEncoded);
        userEntity.setAccountType(userDto.accountType());
        return userEntity;
    }

    @Override
    public UserEntity findUser(Long id) {
        Optional<UserEntity> userEntityOptional = this.userRepository.findUserEntityById(id);
        if(userEntityOptional.isEmpty())
            throw new UserIdNotFoundException("Doesn't exist user "+id);
        else return userEntityOptional.get();
    }

    @Override
    public UserEntity save(UserEntity userEntity) {
        return userRepository.save(userEntity);
    }
}
