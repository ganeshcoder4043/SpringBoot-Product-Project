package com.springbootproject.product.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class CategoryDTOTest {

    private CategoryDTO categoryDTO;

    @BeforeEach
    void setUp() {
        categoryDTO = new CategoryDTO();
    }

    @Test
    void testNoArgConstructor() {
        // Act
        CategoryDTO category = new CategoryDTO();

        // Assert
        assertThat(category).isNotNull();
        assertThat(category.getId()).isNull();
        assertThat(category.getName()).isNull();
        assertThat(category.getProducts()).isNull();
    }

    @Test
    void testAllArgsConstructor() {
        // Arrange
        List<ProductDTO> products = new ArrayList<>();
        ProductDTO product = new ProductDTO(1L, "Product1", "Description1", 100.0, 1L);
        products.add(product);

        // Act
        CategoryDTO category = new CategoryDTO(1L, "Electronics", products);

        // Assert
        assertThat(category).isNotNull();
        assertThat(category.getId()).isEqualTo(1L);
        assertThat(category.getName()).isEqualTo("Electronics");
        assertThat(category.getProducts()).hasSize(1);
        assertThat(category.getProducts().get(0).getName()).isEqualTo("Product1");
    }

    @Test
    void testSetAndGetId() {
        // Act
        categoryDTO.setId(5L);

        // Assert
        assertThat(categoryDTO.getId()).isEqualTo(5L);
    }

    @Test
    void testSetAndGetName() {
        // Act
        categoryDTO.setName("Clothing");

        // Assert
        assertThat(categoryDTO.getName()).isEqualTo("Clothing");
    }

    @Test
    void testSetAndGetProducts() {
        // Arrange
        List<ProductDTO> products = new ArrayList<>();
        ProductDTO product1 = new ProductDTO(1L, "Shirt", "Cotton shirt", 50.0, 1L);
        ProductDTO product2 = new ProductDTO(2L, "Pants", "Denim pants", 75.0, 1L);
        products.add(product1);
        products.add(product2);

        // Act
        categoryDTO.setProducts(products);

        // Assert
        assertThat(categoryDTO.getProducts()).isNotNull();
        assertThat(categoryDTO.getProducts()).hasSize(2);
        assertThat(categoryDTO.getProducts().get(0).getName()).isEqualTo("Shirt");
        assertThat(categoryDTO.getProducts().get(1).getName()).isEqualTo("Pants");
    }

    @Test
    void testProductsInitializationWithEmpty() {
        // Arrange
        categoryDTO.setId(1L);
        categoryDTO.setName("Electronics");
        categoryDTO.setProducts(new ArrayList<>());

        // Assert
        assertThat(categoryDTO.getProducts()).isEmpty();
    }

    @Test
    void testAddProductToCategory() {
        // Arrange
        List<ProductDTO> products = new ArrayList<>();
        categoryDTO.setProducts(products);

        ProductDTO product = new ProductDTO(1L, "Laptop", "Gaming Laptop", 1000.0, 1L);

        // Act
        categoryDTO.getProducts().add(product);

        // Assert
        assertThat(categoryDTO.getProducts()).hasSize(1);
        assertThat(categoryDTO.getProducts().get(0).getName()).isEqualTo("Laptop");
    }

    @Test
    void testEqualsAndHashCode() {
        // Arrange
        CategoryDTO category1 = new CategoryDTO(1L, "Electronics", null);
        CategoryDTO category2 = new CategoryDTO(1L, "Electronics", null);

        // Assert
        assertThat(category1).isEqualTo(category2);
        assertThat(category1.hashCode()).isEqualTo(category2.hashCode());
    }

    @Test
    void testNotEquals() {
        // Arrange
        CategoryDTO category1 = new CategoryDTO(1L, "Electronics", null);
        CategoryDTO category2 = new CategoryDTO(2L, "Clothing", null);

        // Assert
        assertThat(category1).isNotEqualTo(category2);
    }

    @Test
    void testToString() {
        // Arrange
        categoryDTO.setId(1L);
        categoryDTO.setName("Electronics");

        // Act
        String toString = categoryDTO.toString();

        // Assert
        assertThat(toString).isNotNull();
        assertThat(toString).contains("id=1");
        assertThat(toString).contains("name=Electronics");
    }

    @Test
    void testSetMultipleFieldsAndRetrieve() {
        // Arrange
        List<ProductDTO> products = new ArrayList<>();
        ProductDTO product1 = new ProductDTO(1L, "Product1", "Desc1", 100.0, 1L);
        ProductDTO product2 = new ProductDTO(2L, "Product2", "Desc2", 200.0, 1L);
        products.add(product1);
        products.add(product2);

        // Act
        categoryDTO.setId(10L);
        categoryDTO.setName("Books");
        categoryDTO.setProducts(products);

        // Assert
        assertThat(categoryDTO.getId()).isEqualTo(10L);
        assertThat(categoryDTO.getName()).isEqualTo("Books");
        assertThat(categoryDTO.getProducts()).hasSize(2);
    }

    @Test
    void testCategoryWithNullProducts() {
        // Arrange & Act
        CategoryDTO category = new CategoryDTO(1L, "Electronics", null);

        // Assert
        assertThat(category.getProducts()).isNull();
    }

    @Test
    void testCategoryNameWithSpecialCharacters() {
        // Act
        categoryDTO.setName("Electronics & Gadgets");

        // Assert
        assertThat(categoryDTO.getName()).isEqualTo("Electronics & Gadgets");
    }

    @Test
    void testCategoryIdWithLargeValue() {
        // Act
        categoryDTO.setId(999999999L);

        // Assert
        assertThat(categoryDTO.getId()).isEqualTo(999999999L);
    }

    @Test
    void testProductListModification() {
        // Arrange
        List<ProductDTO> products = new ArrayList<>();
        categoryDTO.setProducts(products);
        ProductDTO product = new ProductDTO(1L, "Item", "Description", 50.0, 1L);

        // Act
        categoryDTO.getProducts().add(product);
        int initialSize = categoryDTO.getProducts().size();
        categoryDTO.getProducts().remove(0);
        int finalSize = categoryDTO.getProducts().size();

        // Assert
        assertThat(initialSize).isEqualTo(1);
        assertThat(finalSize).isEqualTo(0);
    }

    @Test
    void testMultipleCategoryInstances() {
        // Arrange & Act
        CategoryDTO category1 = new CategoryDTO(1L, "Category1", null);
        CategoryDTO category2 = new CategoryDTO(2L, "Category2", null);
        CategoryDTO category3 = new CategoryDTO(3L, "Category3", null);

        // Assert
        assertThat(category1.getId()).isEqualTo(1L);
        assertThat(category2.getId()).isEqualTo(2L);
        assertThat(category3.getId()).isEqualTo(3L);
        assertThat(category1).isNotEqualTo(category2);
        assertThat(category2).isNotEqualTo(category3);
    }

    @Test
    void testCategoryDTODataIntegrity() {
        // Arrange
        String categoryName = "Electronics";
        Long categoryId = 100L;
        List<ProductDTO> productList = new ArrayList<>();

        // Act
        categoryDTO.setId(categoryId);
        categoryDTO.setName(categoryName);
        categoryDTO.setProducts(productList);

        // Assert
        assertThat(categoryDTO.getId()).isEqualTo(categoryId);
        assertThat(categoryDTO.getName()).isEqualTo(categoryName);
        assertThat(categoryDTO.getProducts()).isEqualTo(productList);
    }
}
