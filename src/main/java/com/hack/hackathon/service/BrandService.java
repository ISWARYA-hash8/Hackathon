package com.hack.hackathon.service;

import com.hack.hackathon.Entity.Brand;
import java.util.List;

public interface BrandService {

    Brand createBrand(Brand brand);

    List<Brand> getAllBrands();
}