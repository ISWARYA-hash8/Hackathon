package com.hack.hackathon.service.impl;

import com.hack.hackathon.Entity.Brand;
import com.hack.hackathon.repository.BrandRepository;
import com.hack.hackathon.service.BrandService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BrandServiceImpl implements BrandService {

    private final BrandRepository brandRepository;

    @Override
    public Brand createBrand(Brand brand) {
        try {
            return brandRepository.save(brand);
        } catch (Exception e) {
            throw new RuntimeException("Failed to create brand");
        }
    }

    @Override
    public List<Brand> getAllBrands() {
        try {
            return brandRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch brands");
        }
    }
}