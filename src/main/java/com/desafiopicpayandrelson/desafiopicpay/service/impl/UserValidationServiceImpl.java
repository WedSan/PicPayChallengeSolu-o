package com.desafiopicpayandrelson.desafiopicpay.service.impl;

import com.desafiopicpayandrelson.desafiopicpay.dto.UserDto;
import com.desafiopicpayandrelson.desafiopicpay.exception.DuplicateUserDocumentException;
import com.desafiopicpayandrelson.desafiopicpay.exception.DuplicateUserEmailException;
import com.desafiopicpayandrelson.desafiopicpay.repository.UserEntityRepository;
import com.desafiopicpayandrelson.desafiopicpay.service.UserValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserValidationServiceImpl implements UserValidationService {

    private UserEntityRepository userEntityRepository;

    @Autowired
    public UserValidationServiceImpl(UserEntityRepository userEntityRepository) {
        this.userEntityRepository = userEntityRepository;
    }

    @Override
    public void validate(UserDto userDto) {
        validateUser(userDto.email(), userDto.document());
    }

    private void validateUser(String email, String document){
        if(this.userEntityRepository.existsByEmail(email)){
            throw new DuplicateUserEmailException("Account already exists. The email address you're trying to add, has been registered as an account already.");
        }
        else if (this.userEntityRepository.existsByDocument(document)) {
            throw new DuplicateUserDocumentException("Already exists. Another account has been registered with the same document");
        }
    }
}
