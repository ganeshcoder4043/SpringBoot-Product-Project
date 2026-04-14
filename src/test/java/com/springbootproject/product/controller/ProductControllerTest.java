package com.springbootproject.product.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springbootproject.product.dto.ProductDTO;
import com.springbootproject.product.service.ProductService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class ProductControllerTest {

    @InjectMocks
    private ProductController productController;

    @Mock
    private ProductService productService;

    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
        objectMapper = new ObjectMapper();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testCreateProduct() throws Exception {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(1L);
        productDTO.setName("Test Product");
        productDTO.setDescription("Test Description");
        productDTO.setPrice(100.0);
        productDTO.setCategoryId(1L);

        when(productService.createProduct(any(ProductDTO.class))).thenReturn(productDTO);

        mockMvc.perform(post("/api/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(productDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(productDTO.getId()))
                .andExpect(jsonPath("$.name").value(productDTO.getName()))
                .andExpect(jsonPath("$.description").value(productDTO.getDescription()))
                .andExpect(jsonPath("$.price").value(productDTO.getPrice()))
                .andExpect(jsonPath("$.categoryId").value(productDTO.getCategoryId()));
    }

    @Test
    void testGetAllProducts() throws Exception {
        ProductDTO productDTO1 = new ProductDTO(1L, "Product1", "Desc1", 50.0, 1L);
        ProductDTO productDTO2 = new ProductDTO(2L, "Product2", "Desc2", 75.0, 1L);
        List<ProductDTO> productList = List.of(productDTO1, productDTO2);

        when(productService.getAllProducts()).thenReturn(productList);

        mockMvc.perform(get("/api/products"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(productList.size()))
                .andExpect(jsonPath("$[0].name").value(productDTO1.getName()))
                .andExpect(jsonPath("$[1].name").value(productDTO2.getName()));
    }

    @Test
    void testGetProductById() throws Exception {
        ProductDTO productDTO = new ProductDTO(1L, "Test Product", "Test Description", 100.0, 1L);

        when(productService.getProductById(1L)).thenReturn(productDTO);

        mockMvc.perform(get("/api/products/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(productDTO.getId()))
                .andExpect(jsonPath("$.name").value(productDTO.getName()))
                .andExpect(jsonPath("$.description").value(productDTO.getDescription()))
                .andExpect(jsonPath("$.price").value(productDTO.getPrice()))
                .andExpect(jsonPath("$.categoryId").value(productDTO.getCategoryId()));
    }

    @Test
    void testUpdateProduct() throws Exception {
        ProductDTO productDTO = new ProductDTO(1L, "Updated Product", "Updated Description", 150.0, 1L);

        when(productService.updateProduct(eq(1L), any(ProductDTO.class))).thenReturn(productDTO);

        mockMvc.perform(put("/api/products/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(productDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(productDTO.getId()))
                .andExpect(jsonPath("$.name").value(productDTO.getName()))
                .andExpect(jsonPath("$.description").value(productDTO.getDescription()))
                .andExpect(jsonPath("$.price").value(productDTO.getPrice()))
                .andExpect(jsonPath("$.categoryId").value(productDTO.getCategoryId()));
    }

    @Test
    void testDeleteProduct() throws Exception {
        String expectedMessage = "YOUR PRODUCT 1 HAS BEEN SUCCESSFULLY DELETED";

        when(productService.deleteProduct(1L)).thenReturn(expectedMessage);

        mockMvc.perform(delete("/api/products/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(content().string(expectedMessage));
    }
}
