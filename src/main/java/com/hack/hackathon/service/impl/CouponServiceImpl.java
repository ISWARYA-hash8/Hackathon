package com.hack.hackathon.service.impl;

import com.hack.hackathon.Entity.Coupon;
import com.hack.hackathon.repository.CouponRepository;
import com.hack.hackathon.service.CouponService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CouponServiceImpl implements CouponService {

    private final CouponRepository couponRepository;

    @Override
    public Coupon createCoupon(Coupon coupon) {

        try {

            return couponRepository.save(coupon);

        } catch (Exception e) {

            throw new RuntimeException("Failed to create coupon");

        }

    }

    @Override
    public List<Coupon> getAllCoupons() {

        try {

            return couponRepository.findAll();

        } catch (Exception e) {

            throw new RuntimeException("Failed to fetch coupons");

        }

    }
}