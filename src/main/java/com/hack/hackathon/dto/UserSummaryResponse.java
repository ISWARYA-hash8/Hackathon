package com.hack.hackathon.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserSummaryResponse {

    private Long id;
    private String name;
    private String email;
    private String phone;
    private String role;
    private Boolean verified;
}
