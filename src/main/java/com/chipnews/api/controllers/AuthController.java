package com.chipnews.api.controllers;

import com.chipnews.api.dtos.LoginRequest;
import com.chipnews.api.dtos.LoginResponse;
import com.chipnews.api.security.JwtUtils;
import com.chipnews.api.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@Tag(name = "Auth", description = "Users authenticate controller")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    AuthenticationManager authenticationManager;

    @PostMapping("/login")
    @Operation(summary = "Validates user request to allow or deny access")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        String token = JwtUtils.generateToken(request.getEmail());
        return ResponseEntity.ok(new LoginResponse(request.getEmail(), token));
    }


}
