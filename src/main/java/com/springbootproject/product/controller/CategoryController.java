package com.springbootproject.product.controller;

import com.springbootproject.product.dto.CategoryDTO;
import com.springbootproject.product.exception.CategoryAlreadyExistsException;
import com.springbootproject.product.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(
        name = "Category REST API CRUD Operation.",
        description = "Create , Read & Delete Operation For Category REST API."
)
@RestController
@RequestMapping("/api/categories")
@AllArgsConstructor
public class CategoryController {

    private CategoryService categoryService;

    //Create Category
    @ApiResponse(
          responseCode = "201",
          description = "Created"
    )
    @Operation(
            summary = "Create new category",
            description = "Create a new category by providing category details in request body"
    )
    @PostMapping                    //  ↓  JSON to Java Object
    public ResponseEntity<CategoryDTO> createCategory(@RequestBody CategoryDTO categoryDTO){
        return new ResponseEntity<>(categoryService.createCategory(categoryDTO), HttpStatus.CREATED);
    }

    //Create Category
    /*@PostMapping                    //  ↓  JSON to Java Object
    public ResponseEntity<?> createCategory(@RequestBody CategoryDTO categoryDTO){
        try {
            CategoryDTO savedCategory = categoryService.createCategory(categoryDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedCategory);
        }
        catch (CategoryAlreadyExistsException ex){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
        }
    }*/

    // Get all Category
    @Operation(
            summary = "Get all categories",
            description = "Retrieve list of all categories from database"
    )
    @GetMapping
    public List<CategoryDTO>getAllCategories(){
        return categoryService.getAllCategories();
    }


    //Get Category By Id
    @Operation(
            summary = "Get category by ID",
            description = "Retrieve a single category using category ID"
    )
    @GetMapping("/{id}")
    public CategoryDTO getCategoryById(@PathVariable Long id){

        return  categoryService.getCategoryById(id);

    }

    // Delete Category
    @Operation(
            summary = "Delete category",
            description = "Delete category from database using category ID"
    )
    @DeleteMapping("/{id}")
    public String deleteCategory(@PathVariable Long id){
        return categoryService.deleteCategory(id);

    }
}
