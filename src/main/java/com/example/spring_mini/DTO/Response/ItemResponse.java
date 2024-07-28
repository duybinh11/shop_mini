package com.example.spring_mini.DTO.Response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ItemResponse {
    private int id;
    private String name;
    private int price;
}
