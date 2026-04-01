package com.hack.hackathon.service;

import com.hack.hackathon.Entity.Payment;

public interface PaymentService {

    Payment processPayment(Long orderId, String method);
}