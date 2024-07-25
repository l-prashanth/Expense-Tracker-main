package com.prashanth.expense.service;

import com.authy.api.User;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public User findByEmail(String email) {
        // Implement the logic to find a user by email
        // This could involve querying a database or an external service
        // For example:
        return userRepository.findByEmail(email);
    }
}