package com.hack.hackathon.service.impl;

import com.hack.hackathon.Entity.Order;
import com.hack.hackathon.Entity.Payment;
import com.hack.hackathon.exception.ResourceNotFoundException;
import com.hack.hackathon.repository.OrderRepository;
import com.hack.hackathon.repository.PaymentRepository;
import com.hack.hackathon.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final OrderRepository orderRepository;

    @Override
    public Payment processPayment(Long orderId, String method) {

        try {

            Order order = orderRepository.findById(orderId)
                    .orElseThrow(() -> new ResourceNotFoundException("Order not found"));

            Payment payment = new Payment();
            payment.setOrder(order);
            payment.setPaymentMethod(method);

            return paymentRepository.save(payment);

        } catch (Exception e) {

            throw e;

        }

    }
}