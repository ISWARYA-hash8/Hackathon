package com.hack.hackathon.service;

import com.hack.hackathon.Entity.Category;
import java.util.List;

public interface CategoryService {

    Category createCategory(Category category);

    List<Category> getAllCategories();
}