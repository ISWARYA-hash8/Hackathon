package com.hack.hackathon.repository;

import com.hack.hackathon.Entity.CouponUsage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CouponUsageRepository extends JpaRepository<CouponUsage, Long> {

    List<CouponUsage> findByUserId(Long userId);

}