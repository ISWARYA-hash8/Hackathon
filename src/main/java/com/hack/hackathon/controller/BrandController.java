package com.hack.hackathon.controller;

import com.hack.hackathon.Entity.Brand;
import com.hack.hackathon.service.BrandService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/brands")
@RequiredArgsConstructor
public class BrandController {

    private final BrandService brandService;

    @PostMapping
    public ResponseEntity<Brand> createBrand(@RequestBody Brand brand){
        return ResponseEntity.ok(brandService.createBrand(brand));
    }

    @GetMapping
    public ResponseEntity<List<Brand>> getAllBrands(){
        return ResponseEntity.ok(brandService.getAllBrands());
    }
}