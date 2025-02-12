package com.chipnews.api.controllers;

import com.chipnews.api.dtos.UserResponse;
import com.chipnews.api.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
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

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete user by id", description = "Delete user by id")
    public void deleteUserById(@PathVariable Long id) {
        service.deleteUserById(id);
    }
}
