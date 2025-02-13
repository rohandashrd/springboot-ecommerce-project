package com.springboot.ecommerce.service;

import com.springboot.ecommerce.exceptions.APIException;
import com.springboot.ecommerce.exceptions.ResourceNotFoundException;
import com.springboot.ecommerce.model.Category;
import com.springboot.ecommerce.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@Service
public class CategoryServiceImpl implements CategoryService {

//    private List<Category> categories = new ArrayList<>();
//    private Long nextId = 1L;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        if (categories.isEmpty()) {
            throw new APIException("No Categories Found");
        }
        return categories;
    }



    @Override
    public void createCategory(Category category) {
//        category.setCategoryId(nextId++);
        Category savedCategory = categoryRepository.findByCategoryName(category.getCategoryName());
        if (savedCategory != null) {
            throw  new APIException("Category with name "+category.getCategoryName()+" already exists!!");
        }
        categoryRepository.save(category);
    }

    @Override
    public String deleteCategory(Long categoryId) {

        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryId));

        categoryRepository.delete(category);
        return "Category: "+categoryId+" deleted successfully";


//        Below is the code without using JPA
//        List<Category> categories = categoryRepository.findAll();
//
//        Category category=categories.stream()
//                .filter(c -> c.getCategoryId().equals(categoryId))
//                .findFirst()
//                .orElse(null);
//                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Category not found"));
//
//        categoryRepository.delete(category);
//        return "Category with id " + categoryId + " deleted";
    }

    @Override
    public Category updateCategory(Category category, Long categoryId) {

        //Proper code using optional and JPA
        Optional<Category> savedCategoryOptional = categoryRepository.findById(categoryId);

        Category savedCategory = savedCategoryOptional
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryId));

        category.setCategoryId(categoryId);
        savedCategory = categoryRepository.save(category);
        return savedCategory;


        //Below code was used before using JPA
//        List<Category> categories = categoryRepository.findAll();
//
//        Optional<Category> optionalCategory=categories.stream()
//                .filter(c -> c.getCategoryId().equals(categoryId))
//                .findFirst();
//        if (optionalCategory.isPresent()) {
//            Category existingCategory=optionalCategory.get();
//            existingCategory.setCategoryName(category.getCategoryName());
//            Category updatedCategory=categoryRepository.save(existingCategory);
//            return updatedCategory;
//        }else {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Category not found");
//        }
    }
}
