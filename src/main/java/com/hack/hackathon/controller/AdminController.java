package com.hack.hackathon.controller;

import com.hack.hackathon.dto.ApiResponse;
import com.hack.hackathon.dto.UserSummaryResponse;
import com.hack.hackathon.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @GetMapping("/sellers/pending")
    public ResponseEntity<List<UserSummaryResponse>> getPendingSellers() {
        return ResponseEntity.ok(adminService.getPendingSellers());
    }

    @PutMapping("/sellers/{sellerId}/verify")
    public ResponseEntity<ApiResponse> verifySeller(@PathVariable Long sellerId) {
        return ResponseEntity.ok(adminService.verifySeller(sellerId));
    }

    @GetMapping("/portal")
    public ResponseEntity<ApiResponse> adminPortal() {
        return ResponseEntity.ok(new ApiResponse("Welcome to the admin portal"));
    }
}
