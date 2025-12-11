package com.group5.MyMemories.service.impl;

import com.group5.MyMemories.dto.request.LoginRequest;
import com.group5.MyMemories.dto.request.RegisterRequest;
import com.group5.MyMemories.dto.response.LoginResponse;
import com.group5.MyMemories.dto.response.RegisterResponse;
import com.group5.MyMemories.entity.User;
import com.group5.MyMemories.repositories.UserRepository;
import com.group5.MyMemories.service.AuthService;
import com.group5.MyMemories.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Override
    public RegisterResponse register(RegisterRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setEnabled(true);

        userRepository.save(user);

        String token = jwtUtil.generateToken(user.getEmail());

        RegisterResponse response = new RegisterResponse();
        response.setId(user.getId());
        response.setUsername(user.getUsername());
        response.setEmail(user.getEmail());
        response.setToken(token);

        return response;
    }

    @Override
    public LoginResponse login(LoginRequest request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid email or password");
        }

        String token = jwtUtil.generateToken(user.getEmail());

        LoginResponse response = new LoginResponse();
        response.setEmail(user.getEmail());
        response.setToken(token);

        return response;
    }
}
