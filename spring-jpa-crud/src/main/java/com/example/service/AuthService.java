package com.example.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.entity.User;
import com.example.repository.UserRepository;

@Service
public class AuthService {

    private final UserRepository userRepository;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    
    public Optional<User> login(String username, String password) {
        return userRepository.findByUsernameAndPasswordAndActiveTrue(username, password);
    }

   
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

   
    public User save(User user) {
        return userRepository.save(user);
    }
}
