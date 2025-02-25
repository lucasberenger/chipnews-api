package com.chipnews.api.controllers;

import com.chipnews.api.dtos.UserRequest;
import com.chipnews.api.dtos.UserResponse;
import com.chipnews.api.dtos.UserUpdateRequest;
import com.chipnews.api.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@Tag(name = "User", description = "User Management")
public class UserController {

    @Autowired
    UserService service;

    @GetMapping
    @Operation(summary = "Get all users", description = "Return all users")
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        List<UserResponse> users = service.getUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get user by id", description = "Return user by id")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Long id) {
        UserResponse user = service.getUserById(id);
        return ResponseEntity.ok(user);
    }

    @PostMapping
    @Operation(summary = "Create user", description = "Create user")
    public ResponseEntity<UserResponse> createUser(@RequestBody UserRequest request) {
        UserResponse user = service.createUser(request);
        return ResponseEntity.ok(user);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update user by id", description = "Update user by id")
    public ResponseEntity<UserResponse> updateUserById(@PathVariable Long id, @RequestBody UserUpdateRequest request) {
        UserResponse user = service.updateUserById(id, request);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete user by id", description = "Delete user by id")
    public void deleteUserById(@PathVariable Long id) {
        service.deleteUserById(id);
    }


    @GetMapping("/me")
    @Operation(summary = "User info", description = "Show authenticated user info")
    public ResponseEntity<UserResponse> home(Authentication authentication) {
        String email = authentication.getName();
        UserResponse user = service.getUserByEmail(email);
        return ResponseEntity.ok(user);
    }
}
