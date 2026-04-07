package com.springbootproject.product.controller;

import com.springbootproject.product.dto.ProductDTO;
import com.springbootproject.product.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Tag(
        name = "Product REST API CRUD Operation.",
        description = "Create , Read, Update, & Delete Operation For Product REST API."
)
@RestController
@RequestMapping("/api/products")
@AllArgsConstructor
public class ProductController {

    private ProductService productService;

    // Create Product
    @ApiResponse(
            responseCode = "201",
            description = "Created"
    )
    @Operation(
            summary = "Create new product",
            description = "Create a new product by providing product details in request body"
    )
    @PreAuthorize("hasAuthority('ROLE_SELLER')")  // for Single Role
//    @PreAuthorize("hasAnyRole('SELLER','ADMIN')") // for both role
    @PostMapping                                //  ↓  JSON to Java Object
    public ResponseEntity<ProductDTO> createProduct(@RequestBody ProductDTO productDTO){
        return new ResponseEntity<>(productService.createProduct(productDTO), HttpStatus.CREATED);
    }

    // Get All Product
    @Operation(
            summary = "Get all products",
            description = "Retrieve list of all available products from database"
    )
    @GetMapping
    public List<ProductDTO> getAllProducts(){
        return productService.getAllProducts();
    }

    //Get Product By id
    @Operation(
            summary = "Get product by ID",
            description = "Retrieve a single product using product ID"
    )
    @GetMapping("/{id}")
    public ProductDTO getProductById(@PathVariable Long id){
        return productService.getProductById(id);
    }

    // Update Product
    @Operation(
            summary = "Update product",
            description = "Update existing product using ID and new product details"
    )
    @PreAuthorize("hasAuthority('ROLE_SELLER')")
    @PutMapping("/{id}")
    public ProductDTO updateProduct(@PathVariable Long id, @RequestBody ProductDTO productDTO){
        return productService.updateProduct(id,productDTO);
    }

    // Delete Product
    @Operation(
            summary = "Delete product",
            description = "Delete product from database using product ID"
    )
    @PreAuthorize("hasAuthority('ROLE_SELLER')")
    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable Long id){
       return productService.deleteProduct(id);

    }
}
