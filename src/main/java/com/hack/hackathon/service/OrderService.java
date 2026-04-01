package com.hack.hackathon.service;

import com.hack.hackathon.Entity.Order;
import java.util.List;

public interface OrderService {

    Order placeOrder(Long userId, Long addressId);

    List<Order> getUserOrders(Long userId);

    Order getOrderById(Long orderId);
}