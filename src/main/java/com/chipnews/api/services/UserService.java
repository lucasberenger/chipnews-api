package com.chipnews.api.services;

import com.chipnews.api.dtos.UserResponse;
import com.chipnews.api.entities.User;
import com.chipnews.api.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public List<UserResponse> getUsers() {
        return userRepository.findAll()
                .stream()
                .map(UserResponse::new)
                .toList();
    }

    public UserResponse getUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.map(UserResponse::new)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
}
