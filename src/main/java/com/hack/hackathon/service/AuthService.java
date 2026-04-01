package com.hack.hackathon.service;

import com.hack.hackathon.config.JwtUtil;
import com.hack.hackathon.dto.ApiResponse;
import com.hack.hackathon.dto.AuthResponse;
import com.hack.hackathon.dto.LoginRequest;
import com.hack.hackathon.dto.RegisterRequest;
import com.hack.hackathon.entity.Role;
import com.hack.hackathon.entity.User;
import com.hack.hackathon.exception.CustomException;
import com.hack.hackathon.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public ApiResponse register(RegisterRequest request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new CustomException(HttpStatus.BAD_REQUEST, "Email already exists");
        }

        if (request.getRole() == Role.ADMIN) {
            throw new CustomException(HttpStatus.BAD_REQUEST, "Admin cannot register from this endpoint");
        }

        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .phone(request.getPhone())
                .verified(request.getRole() != Role.SELLER)
                .build();

        userRepository.save(user);

        if (request.getRole() == Role.SELLER) {
            return new ApiResponse("Seller registered successfully. Waiting for admin verification.");
        }

        return new ApiResponse("Customer registered successfully.");
    }

    public AuthResponse login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new CustomException(HttpStatus.UNAUTHORIZED, "Invalid email or password"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new CustomException(HttpStatus.UNAUTHORIZED, "Invalid email or password");
        }

        if (user.getRole() == Role.SELLER && !Boolean.TRUE.equals(user.getVerified())) {
            throw new CustomException(HttpStatus.FORBIDDEN, "Seller is not verified by admin");
        }

        return AuthResponse.builder()
                .token(jwtUtil.generateToken(user))
                .role(user.getRole().name())
                .verified(user.getVerified())
                .message("Login successful")
                .build();
    }
}
