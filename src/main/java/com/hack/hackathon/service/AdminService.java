package com.hack.hackathon.service;

import com.hack.hackathon.dto.ApiResponse;
import com.hack.hackathon.dto.UserSummaryResponse;
import com.hack.hackathon.entity.Role;
import com.hack.hackathon.entity.User;
import com.hack.hackathon.exception.CustomException;
import com.hack.hackathon.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final UserRepository userRepository;

    public List<UserSummaryResponse> getPendingSellers() {
        return userRepository.findAllByRoleAndVerifiedFalse(Role.SELLER)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public ApiResponse verifySeller(Long sellerId) {
        User seller = userRepository.findById(sellerId)
                .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "Seller not found"));

        if (seller.getRole() != Role.SELLER) {
            throw new CustomException(HttpStatus.BAD_REQUEST, "User is not a seller");
        }

        if (Boolean.TRUE.equals(seller.getVerified())) {
            return new ApiResponse("Seller is already verified");
        }

        seller.setVerified(true);
        userRepository.save(seller);
        return new ApiResponse("Seller verified successfully");
    }

    private UserSummaryResponse mapToResponse(User user) {
        return UserSummaryResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .phone(user.getPhone())
                .role(user.getRole().name())
                .verified(user.getVerified())
                .build();
    }
}
