package com.example.spring_mini.DTO.Request;

import lombok.Getter;

@Getter
public class RequestCreateUser {
    private String email;
    private String password;
    private String avatar;
}
