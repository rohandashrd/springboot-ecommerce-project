package com.springboot.ecommerce.service;

import com.springboot.ecommerce.exceptions.APIException;
import com.springboot.ecommerce.exceptions.ResourceNotFoundException;
import com.springboot.ecommerce.model.Category;
import com.springboot.ecommerce.payload.CategoryDTO;
import com.springboot.ecommerce.payload.CategoryResponse;
import com.springboot.ecommerce.repositories.CategoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

//    private List<Category> categories = new ArrayList<>();
//    private Long nextId = 1L;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper modelMapper = new ModelMapper();

    @Override
    public CategoryResponse getAllCategories(Integer pageNumber, Integer pageSize,String sortBy,String sortOrder) {

        Sort sortByAndOrder = sortOrder.equalsIgnoreCase("asc")
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageDetails = PageRequest.of(pageNumber, pageSize,sortByAndOrder);
        Page<Category> categoryPage = categoryRepository.findAll(pageDetails);
//        List<Category> categories = categoryRepository.findAll(); below should be used now
        List<Category> categories = categoryPage.getContent();
        if (categories.isEmpty()) {
            throw new APIException("No Categories Found");
        }

        List<CategoryDTO> categoryDTOS=categories.stream()
                .map(category -> modelMapper.map(category, CategoryDTO.class))
                .toList();
        CategoryResponse categoryResponse = new CategoryResponse();
        categoryResponse.setContent(categoryDTOS);
        categoryResponse.setPageNumber(categoryPage.getNumber());
        categoryResponse.setPageSize(categoryPage.getSize());
        categoryResponse.setTotalElements(categoryPage.getTotalElements());
        categoryResponse.setTotalPages(categoryPage.getTotalPages());
        categoryResponse.setLastPage(categoryPage.isLast());


        return categoryResponse;
    }



    @Override
    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
//        category.setCategoryId(nextId++);

        Category category = modelMapper.map(categoryDTO, Category.class);
        Category savedCategoryDB = categoryRepository.findByCategoryName(categoryDTO.getCategoryName());
        if (savedCategoryDB != null) {
            throw  new APIException("Category with name "+categoryDTO.getCategoryName()+" already exists!!");
        }
        Category savedCategory=categoryRepository.save(category);
        CategoryDTO savedCategoryDTO = modelMapper.map(savedCategory, CategoryDTO.class);
        return savedCategoryDTO;
    }

    @Override
    public CategoryDTO deleteCategory(Long categoryId) {

        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryId));

        categoryRepository.delete(category);
        return modelMapper.map(category, CategoryDTO.class);


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
    public CategoryDTO updateCategory(CategoryDTO categoryDTO, Long categoryId) {

        //Proper code using optional and JPA
        Optional<Category> savedCategoryOptional = categoryRepository.findById(categoryId);

        Category savedCategory = savedCategoryOptional
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryId));

        Category category = modelMapper.map(categoryDTO, Category.class);
        categoryDTO.setCategoryId(categoryId);
        savedCategory = categoryRepository.save(category);
        return modelMapper.map(savedCategory, CategoryDTO.class);


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
