package com.hack.hackathon.service;

import com.hack.hackathon.Entity.Coupon;
import java.util.List;

public interface CouponService {

    Coupon createCoupon(Coupon coupon);

    List<Coupon> getAllCoupons();
}