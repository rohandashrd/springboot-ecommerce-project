package com.springboot.ecommerce.controller;

import com.springboot.ecommerce.model.Category;
import com.springboot.ecommerce.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CategoryController {

    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/public/categories")
    public ResponseEntity<List<Category>> getAllCategories(){

        List<Category> categories = categoryService.getAllCategories();
        return new ResponseEntity<>(categories,HttpStatus.OK);
    }

    @PostMapping("/public/categories")
    public ResponseEntity<String> createCategory(@Valid @RequestBody Category category){

        categoryService.createCategory(category);
        return new ResponseEntity<>("Category added successfully",HttpStatus.CREATED);
    }

    @DeleteMapping("/admin/categories/{categoryId}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long categoryId){


        String status = categoryService.deleteCategory(categoryId);
        return new ResponseEntity<>(status,HttpStatus.OK);

//        Since we do not want any business logic part here we removed it
//        try {
//            String status=categoryService.deleteCategory(categoryId);
//            return new ResponseEntity<>(status, HttpStatus.OK);
//            there are other ways to define the above line
//            return ResponseEntity.ok(status);
//            return ResponseEntity.status(HttpStatus.OK).body(status);
//        }catch (ResponseStatusException e){
//            return new ResponseEntity<>(e.getReason(),e.getStatusCode());
//        }
    }

    @PutMapping("/admin/categories/{categoryId}")
    public ResponseEntity<String> updateCategory(@Valid @PathVariable Long categoryId,@RequestBody Category category){


        Category savedCategory=categoryService.updateCategory(category,categoryId);
        return new ResponseEntity<>("Category updated for id: "+categoryId,HttpStatus.OK);


//        Same goes here too no business logic here all handled in serviceImpl
//        try {
//            Category savedCategory= categoryService.updateCategory(category,categoryId);
//            return new ResponseEntity<>("Category updated for id: "+categoryId,HttpStatus.OK);
//        }catch (ResponseStatusException e){
//            return new ResponseEntity<>(e.getReason(),e.getStatusCode());
//        }
    }
}
