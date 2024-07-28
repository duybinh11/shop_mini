package com.example.spring_mini.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpSession;

@RestController
public class TestSessionController {
    @GetMapping("/setDataSeesion")
    public void setData(HttpSession httpSession) {
        httpSession.setAttribute("username", "duybinh");
    }

    @GetMapping("/getDataSeesion")
    public String getData(HttpSession session) {
        String username = (String) session.getAttribute("username");
        return username;
    }
}
