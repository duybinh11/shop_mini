package com.example.spring_mini.DTO.Request;


import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class RequestAddItem {
    @NotBlank(message = "name khong duoc blank")
    private String name;

    @Min(value = 999, message = "price phải lớn hơn hoặc bằng 999")
    @Max(value = 10000000, message = "price không được vượt quá 10000000")
    private int price;
}
