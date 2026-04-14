package com.springbootproject.product.mapper;

import com.springbootproject.product.dto.CategoryDTO;
import com.springbootproject.product.dto.ProductDTO;
import com.springbootproject.product.entity.Category;
import com.springbootproject.product.entity.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class CategoryMapperTest {

    private Category category;
    private CategoryDTO categoryDTO;

    @BeforeEach
    void setUp() {
        // Create test category
        category = new Category();
        category.setId(1L);
        category.setName("Electronics");
        category.setProducts(new ArrayList<>());

        // Create test category DTO
        categoryDTO = new CategoryDTO();
        categoryDTO.setId(1L);
        categoryDTO.setName("Electronics");
        categoryDTO.setProducts(new ArrayList<>());
    }

    @Test
    void testToCategoryDTO() {
        // Act
        CategoryDTO result = CategoryMapper.toCategoryDTO(category);

        // Assert
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(category.getId());
        assertThat(result.getName()).isEqualTo(category.getName());
    }

    @Test
    void testToCategoryDTOWithProducts() {
        // Arrange
        Product product = new Product();
        product.setId(1L);
        product.setName("Laptop");
        product.setDescription("Gaming Laptop");
        product.setPrice(1000.0);
        product.setCategory(category);
        category.getProducts().add(product);

        // Act
        CategoryDTO result = CategoryMapper.toCategoryDTO(category);

        // Assert
        assertThat(result).isNotNull();
        assertThat(result.getProducts()).hasSize(1);
        assertThat(result.getProducts().get(0).getName()).isEqualTo("Laptop");
    }

    @Test
    void testToCategoryDTOWithMultipleProducts() {
        // Arrange
        Product product1 = new Product();
        product1.setId(1L);
        product1.setName("Laptop");
        product1.setCategory(category);

        Product product2 = new Product();
        product2.setId(2L);
        product2.setName("Mouse");
        product2.setCategory(category);

        category.getProducts().add(product1);
        category.getProducts().add(product2);

        // Act
        CategoryDTO result = CategoryMapper.toCategoryDTO(category);

        // Assert
        assertThat(result.getProducts()).hasSize(2);
        assertThat(result.getProducts().get(0).getId()).isEqualTo(1L);
        assertThat(result.getProducts().get(1).getId()).isEqualTo(2L);
    }

    @Test
    void testToCategoryDTOWithNullCategory() {
        // Act
        CategoryDTO result = CategoryMapper.toCategoryDTO(null);

        // Assert
        assertThat(result).isNull();
    }

    @Test
    void testToCategoryDTOWithEmptyProducts() {
        // Arrange
        category.setProducts(new ArrayList<>());

        // Act
        CategoryDTO result = CategoryMapper.toCategoryDTO(category);

        // Assert
        assertThat(result.getProducts()).isEmpty();
    }

    @Test
    void testToCategoryEntity() {
        // Act
        Category result = CategoryMapper.toCategoryEntity(categoryDTO);

        // Assert
        assertThat(result).isNotNull();
        assertThat(result.getName()).isEqualTo(categoryDTO.getName());
        assertThat(result.getId()).isNull(); // ID is not set in entity by mapper
    }

    @Test
    void testToCategoryEntityWithDifferentName() {
        // Arrange
        categoryDTO.setName("Clothing");

        // Act
        Category result = CategoryMapper.toCategoryEntity(categoryDTO);

        // Assert
        assertThat(result.getName()).isEqualTo("Clothing");
    }

    @Test
    void testToCategoryEntityPreservesName() {
        // Arrange
        String categoryName = "Books";
        categoryDTO.setName(categoryName);

        // Act
        Category result = CategoryMapper.toCategoryEntity(categoryDTO);

        // Assert
        assertThat(result.getName()).isEqualTo(categoryName);
    }

    @Test
    void testCategoryDTOToEntityMapping() {
        // Arrange
        CategoryDTO dto = new CategoryDTO(1L, "Furniture", null);

        // Act
        Category entity = CategoryMapper.toCategoryEntity(dto);

        // Assert
        assertThat(entity.getName()).isEqualTo("Furniture");
        assertThat(entity.getId()).isNull(); // ID not transferred
    }

    @Test
    void testCategoryEntityToDTOMapping() {
        // Arrange
        Category cat = new Category();
        cat.setId(5L);
        cat.setName("Sports");
        cat.setProducts(new ArrayList<>());

        // Act
        CategoryDTO dto = CategoryMapper.toCategoryDTO(cat);

        // Assert
        assertThat(dto.getId()).isEqualTo(5L);
        assertThat(dto.getName()).isEqualTo("Sports");
    }

    @Test
    void testToCategoryDTOProductListMapping() {
        // Arrange
        Product product = new Product();
        product.setId(1L);
        product.setName("Shoes");
        product.setDescription("Running Shoes");
        product.setPrice(80.0);
        product.setCategory(category);
        category.getProducts().add(product);

        // Act
        CategoryDTO result = CategoryMapper.toCategoryDTO(category);

        // Assert
        assertThat(result.getProducts()).isNotNull();
        assertThat(result.getProducts()).hasSize(1);
        ProductDTO productDTO = result.getProducts().get(0);
        assertThat(productDTO.getId()).isEqualTo(1L);
        assertThat(productDTO.getName()).isEqualTo("Shoes");
        assertThat(productDTO.getPrice()).isEqualTo(80.0);
    }

    @Test
    void testCategoryNameWithSpecialCharacters() {
        // Arrange
        categoryDTO.setName("Electronics & Gadgets");

        // Act
        Category result = CategoryMapper.toCategoryEntity(categoryDTO);

        // Assert
        assertThat(result.getName()).isEqualTo("Electronics & Gadgets");
    }

    @Test
    void testCategoryNameWithNumbers() {
        // Arrange
        categoryDTO.setName("Category123");

        // Act
        Category result = CategoryMapper.toCategoryEntity(categoryDTO);

        // Assert
        assertThat(result.getName()).isEqualTo("Category123");
    }

    @Test
    void testCategoryWithLongName() {
        // Arrange
        String longName = "This is a very long category name that contains detailed information";
        categoryDTO.setName(longName);

        // Act
        Category result = CategoryMapper.toCategoryEntity(categoryDTO);

        // Assert
        assertThat(result.getName()).isEqualTo(longName);
    }

    @Test
    void testMultipleCategoriesMappingToDTO() {
        // Arrange
        Category cat1 = new Category();
        cat1.setId(1L);
        cat1.setName("Category1");
        cat1.setProducts(new ArrayList<>());

        Category cat2 = new Category();
        cat2.setId(2L);
        cat2.setName("Category2");
        cat2.setProducts(new ArrayList<>());

        // Act
        CategoryDTO dto1 = CategoryMapper.toCategoryDTO(cat1);
        CategoryDTO dto2 = CategoryMapper.toCategoryDTO(cat2);

        // Assert
        assertThat(dto1.getId()).isEqualTo(1L);
        assertThat(dto2.getId()).isEqualTo(2L);
        assertThat(dto1.getName()).isNotEqualTo(dto2.getName());
    }

    @Test
    void testCategoryMapperFieldConsistency() {
        // Arrange
        CategoryDTO originalDTO = new CategoryDTO(1L, "TestCategory", null);

        // Act
        Category entity = CategoryMapper.toCategoryEntity(originalDTO);
        CategoryDTO resultDTO = CategoryMapper.toCategoryDTO(entity);

        // Assert
        assertThat(resultDTO.getName()).isEqualTo(originalDTO.getName());
    }

    @Test
    void testToCategoryDTOProductsNotNull() {
        // Arrange
        category.setProducts(new ArrayList<>());

        // Act
        CategoryDTO result = CategoryMapper.toCategoryDTO(category);

        // Assert
        assertThat(result.getProducts()).isNotNull();
    }

    @Test
    void testToCategoryEntityProductsNotModified() {
        // Arrange
        categoryDTO.setProducts(new ArrayList<>());

        // Act
        Category result = CategoryMapper.toCategoryEntity(categoryDTO);

        // Assert
        assertThat(result.getProducts()).isNotNull();
        assertThat(result.getProducts()).isEmpty();
    }

    @Test
    void testCategoryIdPreservedInDTO() {
        // Arrange
        category.setId(100L);

        // Act
        CategoryDTO result = CategoryMapper.toCategoryDTO(category);

        // Assert
        assertThat(result.getId()).isEqualTo(100L);
    }

    @Test
    void testCategoryIdNotTransferredToEntity() {
        // Arrange
        categoryDTO.setId(50L);

        // Act
        Category result = CategoryMapper.toCategoryEntity(categoryDTO);

        // Assert
        assertThat(result.getId()).isNull();
    }
}
