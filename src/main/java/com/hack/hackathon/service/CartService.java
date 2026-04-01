package com.hack.hackathon.service;

import com.hack.hackathon.Entity.Cart;

public interface CartService {

    Cart getCartByUser(Long userId);

    void addToCart(Long userId, Long productId, int quantity);

    void removeItem(Long cartItemId);

    void clearCart(Long userId);
}