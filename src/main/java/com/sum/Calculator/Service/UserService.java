package com.sum.Calculator.service;

import com.sum.Calculator.models.User;
import com.sum.Calculator.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

@org.springframework.stereotype.Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User findId(Long id) {
        return userRepository.findById(id).orElse(null);
    }
}
