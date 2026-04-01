package com.hack.hackathon.service.impl;

import com.hack.hackathon.Entity.User;
import com.hack.hackathon.exception.ResourceNotFoundException;
import com.hack.hackathon.repository.UserRepository;
import com.hack.hackathon.service.UserService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User createUser(User user) {

        try {

            if (userRepository.existsByEmail(user.getEmail())) {
                throw new RuntimeException("Email already exists");
            }

            return userRepository.save(user);

        } catch (Exception e) {
            throw new RuntimeException("Error creating user");
        }
    }

    @Override
    public List<User> getAllUsers() {

        try {
            return userRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Error fetching users");
        }

    }

    @Override
    public User getUserById(Long id) {

        try {

            return userRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("User not found with id " + id));

        } catch (Exception e) {
            throw new RuntimeException("Error fetching user");
        }

    }

    @Override
    public User updateUser(Long id, User user) {

        try {

            User existingUser = userRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("User not found"));

            existingUser.setName(user.getName());
            existingUser.setEmail(user.getEmail());
            existingUser.setPhone(user.getPhone());
            existingUser.setPassword(user.getPassword());
            existingUser.setRole(user.getRole());

            return userRepository.save(existingUser);

        } catch (Exception e) {
            throw new RuntimeException("Error updating user");
        }

    }

    @Override
    public void deleteUser(Long id) {

        try {

            User user = userRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("User not found"));

            userRepository.delete(user);

        } catch (Exception e) {
            throw new RuntimeException("Error deleting user");
        }

    }
}