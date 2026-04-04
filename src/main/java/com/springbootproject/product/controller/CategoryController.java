package com.springbootproject.product.controller;

import com.springbootproject.product.dto.CategoryDTO;
import com.springbootproject.product.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@AllArgsConstructor
public class CategoryController {

    private CategoryService categoryService;

    //Create Category
    @PostMapping                    //  ↓  JSON to Java Object
    public ResponseEntity<CategoryDTO> createCategory(@RequestBody CategoryDTO categoryDTO){
        return new ResponseEntity<>(categoryService.createCategory(categoryDTO), HttpStatus.CREATED);
    }

    // Get all Category
    @GetMapping
    public List<CategoryDTO>getAllCategories(){
        return categoryService.getAllCategories();
    }

    //Get Category By Id
    @GetMapping("/{id}")
    public CategoryDTO getCategoryById(@PathVariable Long id){

        return  categoryService.getCategoryById(id);

    }

    // Delete Category
    @DeleteMapping("/{id}")
    public String deleteCategory(@PathVariable Long id){
        return categoryService.deleteCategory(id);

    }
}
