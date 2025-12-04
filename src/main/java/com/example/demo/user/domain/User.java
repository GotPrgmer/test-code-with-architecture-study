package com.example.demo.user.domain;

import com.example.demo.common.domain.exception.CertificationCodeNotMatchedException;
import com.example.demo.common.domain.exception.ResourceNotFoundException;
import lombok.Builder;
import lombok.Getter;

import java.time.Clock;
import java.util.UUID;

@Getter
public class User {
    private final Long id;

    private final String email;

    private final String nickname;

    private final String address;

    private final String certificationCode;

    private final UserStatus status;

    private final Long lastLoginAt;

    @Builder
    public User (Long id, String email, String nickname, String address, String certificationCode, UserStatus status, Long lastLoginAt){
        this.id = id;
        this.email = email;
        this.nickname = nickname;
        this.address = address;
        this.certificationCode =certificationCode;
        this.status = status;
        this.lastLoginAt = lastLoginAt;
    }

    public static User from(UserCreate userCreate){
        return User.builder()
                .email(userCreate.getEmail())
                .address(userCreate.getAddress())
                .nickname(userCreate.getNickname())
                .certificationCode(UUID.randomUUID().toString())
                .status(UserStatus.PENDING)
                .build();
    }

    public User update(UserUpdate userUpdate) {
        return User.builder()
                .id(id)
                .email(email)
                .certificationCode(certificationCode)
                .status(status)
                .lastLoginAt(lastLoginAt)
                .nickname(userUpdate.getNickname())
                .address(userUpdate.getAddress())
                .build();
    }
    public User login(){
        return User.builder()
                .id(id)
                .email(email)
                .certificationCode(certificationCode)
                .status(status)
                .lastLoginAt(Clock.systemUTC().millis())
                .nickname(nickname)
                .address(address)
                .build();
    }
    public User certificate(String certificationCode){
        return User.builder()
                .id(id)
                .email(email)
                .certificationCode(certificationCode)
                .status(UserStatus.ACTIVE)
                .lastLoginAt(lastLoginAt)
                .nickname(nickname)
                .address(address)
                .build();
    }
}
