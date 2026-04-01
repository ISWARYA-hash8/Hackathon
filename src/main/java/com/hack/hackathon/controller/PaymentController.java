package com.hack.hackathon.controller;

import com.hack.hackathon.Entity.Payment;
import com.hack.hackathon.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping
    public ResponseEntity<Payment> processPayment(
            @RequestParam Long orderId,
            @RequestParam String method){

        return ResponseEntity.ok(paymentService.processPayment(orderId,method));
    }
}