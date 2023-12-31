package com.desafiopicpayandrelson.desafiopicpay.repository;

import com.desafiopicpayandrelson.desafiopicpay.model.User.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserEntityRepository extends JpaRepository<UserEntity, Long> {

    boolean existsByEmail(String email);

    boolean existsByDocument(String document);

    Optional<UserEntity> findUserEntityById(Long id);

}
