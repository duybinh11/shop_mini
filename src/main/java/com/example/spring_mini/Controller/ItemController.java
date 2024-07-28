package com.example.spring_mini.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring_mini.DTO.Response.ResponseData;
import com.example.spring_mini.Entity.UserEntity;
import com.example.spring_mini.Service.AuthenticationService;
import com.example.spring_mini.Service.ItemService;

import jakarta.validation.Valid;

import com.example.spring_mini.DTO.Request.RequestAddItem;
import com.example.spring_mini.DTO.Request.RequestLogin;

@RestController
public class ItemController {
    @Autowired
    private ItemService itemService;

    @GetMapping("/getAllItem")
    public ResponseData getAllItem(@RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) {
        return itemService.getAllItem(page, size);
    }

    @PostMapping("/addItem")
    public ResponseData addItem(@RequestBody @Valid RequestAddItem requestAddItem) {
        return itemService.addItem(requestAddItem);
    }

    @DeleteMapping("/deleteItem/{id}")
    public ResponseData deleteItem(@PathVariable int id) {
        return itemService.removeItem(id);
    }



}
