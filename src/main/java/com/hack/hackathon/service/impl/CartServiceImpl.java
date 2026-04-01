package com.hack.hackathon.service.impl;

import com.hack.hackathon.Entity.*;
import com.hack.hackathon.exception.ResourceNotFoundException;
import com.hack.hackathon.repository.*;

import com.hack.hackathon.service.CartService;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final CartItemRepository cartItemRepository;

    @Override
    public Cart getCartByUser(Long userId) {

        try {

            return cartRepository.findByUserId(userId)
                    .orElseThrow(() -> new ResourceNotFoundException("Cart not found"));

        } catch (Exception e) {

            throw e;

        }

    }

    @Override
    public void addToCart(Long userId, Long productId, int quantity) {

        try {

            Cart cart = cartRepository.findByUserId(userId)
                    .orElseThrow(() -> new ResourceNotFoundException("Cart not found"));

            Product product = productRepository.findById(productId)
                    .orElseThrow(() -> new ResourceNotFoundException("Product not found"));

            CartItem item = new CartItem();
            item.setCart(cart);
            item.setProduct(product);
            item.setQuantity(quantity);

            cartItemRepository.save(item);

        } catch (Exception e) {

            throw e;

        }

    }

    @Override
    public void removeItem(Long cartItemId) {

        try {

            CartItem item = cartItemRepository.findById(cartItemId)
                    .orElseThrow(() -> new ResourceNotFoundException("Cart item not found"));

            cartItemRepository.delete(item);

        } catch (Exception e) {

            throw e;

        }

    }

    @Override
    public void clearCart(Long userId) {

        try {

            Cart cart = cartRepository.findByUserId(userId)
                    .orElseThrow(() -> new ResourceNotFoundException("Cart not found"));

            // delete all cart items
            cartItemRepository.deleteAll(cart.getItems());

        } catch (Exception e) {

            throw new RuntimeException("Error clearing cart");

        }

    }
}