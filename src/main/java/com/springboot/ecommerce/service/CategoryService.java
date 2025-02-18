package com.springboot.ecommerce.service;

import com.springboot.ecommerce.model.Category;
import com.springboot.ecommerce.payload.CategoryDTO;
import com.springboot.ecommerce.payload.CategoryResponse;

import java.util.List;

public interface CategoryService {

    CategoryResponse getAllCategories(Integer pageNumber, Integer pageSize,String sortBy,String sortOrder);
    CategoryDTO createCategory(CategoryDTO categoryDTO);

    CategoryDTO deleteCategory(Long categoryId);

    CategoryDTO updateCategory(CategoryDTO categoryDTO, Long categoryId);
}
