package com.hack.hackathon.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AuthResponse {

    private String token;
    private String role;
    private Boolean verified;
    private String message;
}
