package com.example.spring_mini.DTO.Response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Builder
@Getter
public class UserCreateResponse {
    private String email;
    private String avatar;
}
