package com.hack.hackathon.repository;

import com.hack.hackathon.entity.Role;
import com.hack.hackathon.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    List<User> findAllByRoleAndVerifiedFalse(Role role);
}
