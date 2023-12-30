package com.desafiopicpayandrelson.desafiopicpay.controller;

import com.desafiopicpayandrelson.desafiopicpay.dto.UserDto;
import com.desafiopicpayandrelson.desafiopicpay.model.User.UserEntity;
import com.desafiopicpayandrelson.desafiopicpay.service.UserService;
import com.desafiopicpayandrelson.desafiopicpay.service.UserValidationService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/user")
public class UserController {

    private final UserService userService;

    private final UserValidationService userValidation;

    @Autowired
    public UserController(UserService userService, UserValidationService userValidation) {
        this.userService = userService;
        this.userValidation = userValidation;
    }


    @PostMapping("register")
    @Transactional
    public ResponseEntity<UserEntity> register(@RequestBody @Valid UserDto userDto) {
        this.userValidation.validate(userDto);
        UserEntity userEntity = this.userService.create(userDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(this.userService.save(userEntity));
    }
}
