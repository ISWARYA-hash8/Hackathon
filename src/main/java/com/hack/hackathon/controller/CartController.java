package com.hack.hackathon.controller;

import com.hack.hackathon.Entity.Cart;
import com.hack.hackathon.service.CartService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    // Get cart by user
    @GetMapping("/{userId}")
    public ResponseEntity<Cart> getCart(@PathVariable Long userId) {

        Cart cart = cartService.getCartByUser(userId);
        return ResponseEntity.ok(cart);

    }

    // Add item to cart
    @PostMapping("/add")
    public ResponseEntity<String> addToCart(
            @RequestParam Long userId,
            @RequestParam Long productId,
            @RequestParam int quantity) {

        cartService.addToCart(userId, productId, quantity);
        return ResponseEntity.ok("Item added to cart successfully");

    }

    // Remove item from cart
    @DeleteMapping("/remove/{cartItemId}")
    public ResponseEntity<String> removeItem(@PathVariable Long cartItemId) {

        cartService.removeItem(cartItemId);
        return ResponseEntity.ok("Item removed from cart successfully");

    }

    // Clear full cart (optional but industry standard)
    @DeleteMapping("/clear/{userId}")
    public ResponseEntity<String> clearCart(@PathVariable Long userId) {

        cartService.clearCart(userId);
        return ResponseEntity.ok("Cart cleared successfully");

    }
}