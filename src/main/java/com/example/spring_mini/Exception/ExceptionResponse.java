package com.example.spring_mini.Exception;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ExceptionResponse {
    private int code;
    private Object message;
}
