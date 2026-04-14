package com.springbootproject.product.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springbootproject.product.dto.CategoryDTO;
import com.springbootproject.product.service.CategoryService;
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
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class CategoryControllerTest {

    @InjectMocks
    private CategoryController categoryController;

    @Mock
    private CategoryService categoryService;

    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(categoryController).build();
        objectMapper = new ObjectMapper();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testGetAllCategories_Success() throws Exception {
        CategoryDTO categoryDTO1 = new CategoryDTO();
        categoryDTO1.setId(1L);
        categoryDTO1.setName("Electronics");

        CategoryDTO categoryDTO2 = new CategoryDTO();
        categoryDTO2.setId(2L);
        categoryDTO2.setName("Clothing");

        List<CategoryDTO> categoryList = List.of(categoryDTO1, categoryDTO2);

        when(categoryService.getAllCategories()).thenReturn(categoryList);

        mockMvc.perform(get("/api/categories"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(categoryList.size()))
                .andExpect(jsonPath("$[0].id").value(categoryDTO1.getId()))
                .andExpect(jsonPath("$[0].name").value(categoryDTO1.getName()))
                .andExpect(jsonPath("$[1].id").value(categoryDTO2.getId()))
                .andExpect(jsonPath("$[1].name").value(categoryDTO2.getName()));
    }

    @Test
    void testGetAllCategories_EmptyList() throws Exception {
        when(categoryService.getAllCategories()).thenReturn(List.of());

        mockMvc.perform(get("/api/categories"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(0));
    }

    @Test
    void testCreateCategory_Success() throws Exception {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(1L);
        categoryDTO.setName("Electronics");

        when(categoryService.createCategory(any(CategoryDTO.class))).thenReturn(categoryDTO);

        mockMvc.perform(post("/api/categories")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(categoryDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(categoryDTO.getId()))
                .andExpect(jsonPath("$.name").value(categoryDTO.getName()));
    }

    @Test
    void testCreateCategory_WithJsonString() throws Exception {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(1L);
        categoryDTO.setName("Test Category");

        when(categoryService.createCategory(any(CategoryDTO.class))).thenReturn(categoryDTO);

        mockMvc.perform(post("/api/categories")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "    \"id\": 1,\n" +
                                "    \"name\": \"Test Category\"\n" +
                                "}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value(categoryDTO.getName()));
    }

    @Test
    void testGetCategoryById_Success() throws Exception {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(1L);
        categoryDTO.setName("Electronics");

        when(categoryService.getCategoryById(1L)).thenReturn(categoryDTO);

        mockMvc.perform(get("/api/categories/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(categoryDTO.getId()))
                .andExpect(jsonPath("$.name").value(categoryDTO.getName()));
    }

    @Test
    void testGetCategoryById_DifferentId() throws Exception {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(5L);
        categoryDTO.setName("Furniture");

        when(categoryService.getCategoryById(5L)).thenReturn(categoryDTO);

        mockMvc.perform(get("/api/categories/{id}", 5L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(5L))
                .andExpect(jsonPath("$.name").value("Furniture"));
    }

    @Test
    void testDeleteCategory_Success() throws Exception {
        String expectedMessage = "YOUR CATEGORY 1 HAS BEEN SUCCESSFULLY DELETED";

        when(categoryService.deleteCategory(1L)).thenReturn(expectedMessage);

        mockMvc.perform(delete("/api/categories/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(content().string(expectedMessage));
    }

    @Test
    void testDeleteCategory_DifferentId() throws Exception {
        String expectedMessage = "YOUR CATEGORY 10 HAS BEEN SUCCESSFULLY DELETED";

        when(categoryService.deleteCategory(10L)).thenReturn(expectedMessage);

        mockMvc.perform(delete("/api/categories/{id}", 10L))
                .andExpect(status().isOk())
                .andExpect(content().string(expectedMessage));
    }

    @Test
    void testCreateCategory_MultipleCategories() throws Exception {
        CategoryDTO category1 = new CategoryDTO(1L, "Electronics", null);
        when(categoryService.createCategory(any(CategoryDTO.class))).thenReturn(category1);

        mockMvc.perform(post("/api/categories")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(category1)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Electronics"));

        CategoryDTO category2 = new CategoryDTO(2L, "Clothing", null);
        when(categoryService.createCategory(any(CategoryDTO.class))).thenReturn(category2);

        mockMvc.perform(post("/api/categories")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(category2)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Clothing"));
    }

    @Test
    void testGetAllCategories_VerifyResponseFormat() throws Exception {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(1L);
        categoryDTO.setName("Books");
        categoryDTO.setProducts(null);

        when(categoryService.getAllCategories()).thenReturn(List.of(categoryDTO));

        mockMvc.perform(get("/api/categories")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").isNumber())
                .andExpect(jsonPath("$[0].name").isString());
    }
}