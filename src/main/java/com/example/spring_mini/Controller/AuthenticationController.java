package com.example.spring_mini.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring_mini.DTO.Request.RequestCreateUser;
import com.example.spring_mini.DTO.Request.RequestLogin;
import com.example.spring_mini.DTO.Request.RequestLoginToken;
import com.example.spring_mini.DTO.Response.ResponseData;
import com.example.spring_mini.Entity.UserEntity;
import com.example.spring_mini.Service.AuthenticationService;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/login")
    public UserEntity login(@RequestBody RequestLogin requestLogin) {
        return authenticationService.loginSecurity(requestLogin);

    }

    @PostMapping("/createUser")
    public ResponseData createUser(@RequestBody RequestCreateUser requestCreateUser) {
        return authenticationService.createUser(requestCreateUser);
    }

    @PostMapping("/getToken")
    public String getToken(@RequestBody RequestLogin requestLogin) {
        return authenticationService.createToken(requestLogin);
    }

    @PostMapping("/token")
    public boolean giaiMa(@RequestBody RequestLoginToken requestLoginToken) {
        return authenticationService.verifyToken(requestLoginToken);
    }

    

}
