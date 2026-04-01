package com.hack.hackathon.controller;

import com.hack.hackathon.Entity.Order;
import com.hack.hackathon.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/place")
    public ResponseEntity<Order> placeOrder(
            @RequestParam Long userId,
            @RequestParam Long addressId){

        return ResponseEntity.ok(orderService.placeOrder(userId,addressId));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Order>> getOrders(@PathVariable Long userId){
        return ResponseEntity.ok(orderService.getUserOrders(userId));
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<Order> getOrder(@PathVariable Long orderId){
        return ResponseEntity.ok(orderService.getOrderById(orderId));
    }
}