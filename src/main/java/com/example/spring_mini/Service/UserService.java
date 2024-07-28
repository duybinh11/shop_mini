package com.example.spring_mini.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.example.spring_mini.Repository.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public UserDetailsService getUserDetailsService() {
        return email -> userRepository.findByEmail(email);
    }

}
