package com.example.spring_mini.DTO.Response;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
@Getter
@Setter
public class ResponseData extends ResponseEntity<ResponseData.Payload> {

    public ResponseData(HttpStatusCode status, String message) {
        super(new Payload(status.value(), message), status);
    }

    public ResponseData(HttpStatusCode status, String message, Object data) {
        super(new Payload(status.value(), message, data), status);
    }

    public ResponseData(HttpStatusCode status, String message, Object data, int totalPage) {
        super(new Payload(status.value(), message, totalPage, data), status);
    }

    @Getter
    @Setter
    @RequiredArgsConstructor
    @AllArgsConstructor
    public static class Payload {
        private final int code;
        private final String message;
        private int totalPage;
        private Object data;

        public Payload(int code, String message, Object data) {
            this.code = code;
            this.message = message;
            this.data = data;
        }
    }
}