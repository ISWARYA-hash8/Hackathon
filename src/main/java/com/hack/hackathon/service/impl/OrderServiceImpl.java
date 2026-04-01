package com.hack.hackathon.service.impl;

import com.hack.hackathon.Entity.*;
import com.hack.hackathon.exception.ResourceNotFoundException;
import com.hack.hackathon.repository.*;
import com.hack.hackathon.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final AddressRepository addressRepository;

    @Override
    public Order placeOrder(Long userId, Long addressId) {

        try {

            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new ResourceNotFoundException("User not found"));

            Address address = addressRepository.findById(addressId)
                    .orElseThrow(() -> new ResourceNotFoundException("Address not found"));

            Order order = new Order();
            order.setUser(user);
            order.setAddress(address);

            return orderRepository.save(order);

        } catch (Exception e) {

            throw e;

        }

    }

    @Override
    public List<Order> getUserOrders(Long userId) {

        try {

            return orderRepository.findByUserId(userId);

        } catch (Exception e) {

            throw new RuntimeException("Failed to fetch orders");

        }

    }

    @Override
    public Order getOrderById(Long orderId) {

        try {

            return orderRepository.findById(orderId)
                    .orElseThrow(() -> new ResourceNotFoundException("Order not found"));

        } catch (Exception e) {

            throw e;

        }

    }
}