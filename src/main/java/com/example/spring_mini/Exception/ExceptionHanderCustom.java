package com.example.spring_mini.Exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.fasterxml.jackson.databind.exc.InvalidDefinitionException;

@RestControllerAdvice
public class ExceptionHanderCustom {

    @ExceptionHandler(InvalidDefinitionException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionResponse handleInvalidDefinitionException(Exception e, WebRequest webRequest) {

        return ExceptionResponse.builder()
                .code(HttpStatus.BAD_REQUEST.value())
                .message(e.getMessage())
                .build();

    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionResponse handleException(MethodArgumentNotValidException e, WebRequest webRequest) {
        @SuppressWarnings({ "rawtypes", "unchecked" })
        Map<String, String> mapError = new HashMap();
        e.getBindingResult().getFieldErrors().forEach(errorItem -> {
            mapError.put(errorItem.getField(), errorItem.getDefaultMessage());
        });

        return ExceptionResponse.builder()
                .code(HttpStatus.BAD_REQUEST.value())
                .message(mapError)
                .build();

    }
}
