package com.chipnews.api.services;

import com.chipnews.api.dtos.UserRequest;
import com.chipnews.api.dtos.UserResponse;
import com.chipnews.api.dtos.UserUpdateRequest;
import com.chipnews.api.entities.User;
import com.chipnews.api.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    //TODO: Create custom exception for User not found!!

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

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

    @Transactional
    public UserResponse createUser(UserRequest request) {
        User user = new User();
        BeanUtils.copyProperties(request, user);
        // TODO: Treat the case when some field is null.

        if (request.getPassword() != null) {
            user.setPassword(passwordEncoder.encode(request.getPassword()));
        }

        userRepository.save(user);
        return new UserResponse(user);
    }

    @Transactional
    public UserResponse updateUserById(Long id, UserUpdateRequest request) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // TODO: Treat the case when some field is null.
        BeanUtils.copyProperties(request, user);

        userRepository.save(user);
        return new UserResponse(user);
    }

    @Transactional
    public void deleteUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        userRepository.delete(user);
    }
}
