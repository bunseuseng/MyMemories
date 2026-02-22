package com.group5.MyMemories.controller;

import com.group5.MyMemories.dto.request.LoginRequest;
import com.group5.MyMemories.dto.request.RegisterRequest;
import com.group5.MyMemories.dto.response.LoginResponse;
import com.group5.MyMemories.dto.response.RegisterResponse;
import com.group5.MyMemories.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(
            @RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(
            @RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }
}
