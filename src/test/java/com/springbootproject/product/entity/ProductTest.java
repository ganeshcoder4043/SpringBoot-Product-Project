package com.springbootproject.product.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class ProductTest {

    private Product product;
    private Category category;

    @BeforeEach
    void setUp() {
        product = new Product();
        category = new Category();
        category.setId(1L);
        category.setName("Electronics");
    }

    @Test
    void testProductConstructor() {
        // Act
        Product newProduct = new Product();

        // Assert
        assertThat(newProduct).isNotNull();
        assertThat(newProduct.getId()).isNull();
        assertThat(newProduct.getName()).isNull();
        assertThat(newProduct.getDescription()).isNull();
        assertThat(newProduct.getPrice()).isNull();
        assertThat(newProduct.getCategory()).isNull();
    }

    @Test
    void testSetAndGetId() {
        // Act
        product.setId(1L);

        // Assert
        assertThat(product.getId()).isEqualTo(1L);
    }

    @Test
    void testSetAndGetName() {
        // Act
        product.setName("Laptop");

        // Assert
        assertThat(product.getName()).isEqualTo("Laptop");
    }

    @Test
    void testSetAndGetDescription() {
        // Act
        product.setDescription("Gaming Laptop with RTX 3080");

        // Assert
        assertThat(product.getDescription()).isEqualTo("Gaming Laptop with RTX 3080");
    }

    @Test
    void testSetAndGetPrice() {
        // Act
        product.setPrice(1000.0);

        // Assert
        assertThat(product.getPrice()).isEqualTo(1000.0);
    }

    @Test
    void testSetAndGetCategory() {
        // Act
        product.setCategory(category);

        // Assert
        assertThat(product.getCategory()).isNotNull();
        assertThat(product.getCategory().getId()).isEqualTo(1L);
        assertThat(product.getCategory().getName()).isEqualTo("Electronics");
    }

    @Test
    void testSetAllFields() {
        // Arrange
        Long id = 5L;
        String name = "Mouse";
        String description = "Wireless Mouse";
        Double price = 50.0;

        // Act
        product.setId(id);
        product.setName(name);
        product.setDescription(description);
        product.setPrice(price);
        product.setCategory(category);

        // Assert
        assertThat(product.getId()).isEqualTo(id);
        assertThat(product.getName()).isEqualTo(name);
        assertThat(product.getDescription()).isEqualTo(description);
        assertThat(product.getPrice()).isEqualTo(price);
        assertThat(product.getCategory()).isEqualTo(category);
    }

    @Test
    void testProductNameWithSpecialCharacters() {
        // Act
        product.setName("Product & Service");

        // Assert
        assertThat(product.getName()).isEqualTo("Product & Service");
    }

    @Test
    void testProductDescriptionWithSpecialCharacters() {
        // Act
        product.setDescription("High-quality @ discount!");

        // Assert
        assertThat(product.getDescription()).isEqualTo("High-quality @ discount!");
    }

    @Test
    void testProductIdWithLargeValue() {
        // Act
        product.setId(999999999L);

        // Assert
        assertThat(product.getId()).isEqualTo(999999999L);
    }

    @Test
    void testProductPriceWithLargeValue() {
        // Act
        product.setPrice(999999.99);

        // Assert
        assertThat(product.getPrice()).isEqualTo(999999.99);
    }

    @Test
    void testProductPriceWithZero() {
        // Act
        product.setPrice(0.0);

        // Assert
        assertThat(product.getPrice()).isEqualTo(0.0);
    }

    @Test
    void testProductPriceWithDecimal() {
        // Act
        product.setPrice(99.99);

        // Assert
        assertThat(product.getPrice()).isEqualTo(99.99);
    }

    @Test
    void testProductPriceWithNegativeValue() {
        // Act
        product.setPrice(-100.0);

        // Assert
        assertThat(product.getPrice()).isEqualTo(-100.0);
    }

    @Test
    void testProductWithNullValues() {
        // Act & Assert
        assertThat(product.getId()).isNull();
        assertThat(product.getName()).isNull();
        assertThat(product.getDescription()).isNull();
        assertThat(product.getPrice()).isNull();
        assertThat(product.getCategory()).isNull();
    }

    @Test
    void testProductNameWithEmptyString() {
        // Act
        product.setName("");

        // Assert
        assertThat(product.getName()).isEqualTo("");
    }

    @Test
    void testProductDescriptionWithEmptyString() {
        // Act
        product.setDescription("");

        // Assert
        assertThat(product.getDescription()).isEqualTo("");
    }

    @Test
    void testProductNameWithLongText() {
        // Arrange
        String longName = "This is a very long product name that contains multiple words and detailed information";

        // Act
        product.setName(longName);

        // Assert
        assertThat(product.getName()).isEqualTo(longName);
    }

    @Test
    void testProductDescriptionWithLongText() {
        // Arrange
        String longDescription = "This is a very long description " +
                "that contains multiple lines and detailed information " +
                "about the product specifications and features";

        // Act
        product.setDescription(longDescription);

        // Assert
        assertThat(product.getDescription()).isEqualTo(longDescription);
    }

    @Test
    void testMultipleProductInstances() {
        // Arrange & Act
        Product product1 = new Product();
        product1.setId(1L);
        product1.setName("Product1");

        Product product2 = new Product();
        product2.setId(2L);
        product2.setName("Product2");

        Product product3 = new Product();
        product3.setId(3L);
        product3.setName("Product3");

        // Assert
        assertThat(product1.getId()).isEqualTo(1L);
        assertThat(product2.getId()).isEqualTo(2L);
        assertThat(product3.getId()).isEqualTo(3L);
    }

    @Test
    void testProductDataIntegrity() {
        // Arrange
        Long id = 50L;
        String name = "Keyboard";
        String description = "Mechanical Keyboard";
        Double price = 150.0;

        // Act
        product.setId(id);
        product.setName(name);
        product.setDescription(description);
        product.setPrice(price);
        product.setCategory(category);

        // Assert
        assertThat(product.getId()).isEqualTo(id);
        assertThat(product.getName()).isEqualTo(name);
        assertThat(product.getDescription()).isEqualTo(description);
        assertThat(product.getPrice()).isEqualTo(price);
        assertThat(product.getCategory()).isEqualTo(category);
    }

    @Test
    void testProductModification() {
        // Arrange
        product.setId(1L);
        product.setName("Original");
        product.setPrice(100.0);

        // Act
        product.setName("Modified");
        product.setPrice(150.0);

        // Assert
        assertThat(product.getId()).isEqualTo(1L);
        assertThat(product.getName()).isEqualTo("Modified");
        assertThat(product.getPrice()).isEqualTo(150.0);
    }

    @Test
    void testProductWithMinimumPrice() {
        // Act
        product.setPrice(0.01);

        // Assert
        assertThat(product.getPrice()).isEqualTo(0.01);
    }

    @Test
    void testProductNameWithNumbers() {
        // Act
        product.setName("Product123");

        // Assert
        assertThat(product.getName()).isEqualTo("Product123");
    }

    @Test
    void testProductDescriptionWithNumbers() {
        // Act
        product.setDescription("Product with version 2.0 and model 3D");

        // Assert
        assertThat(product.getDescription()).isEqualTo("Product with version 2.0 and model 3D");
    }

    @Test
    void testProductCategoryRelationship() {
        // Arrange
        Category category1 = new Category();
        category1.setId(1L);
        category1.setName("Electronics");

        // Act
        product.setCategory(category1);

        // Assert
        assertThat(product.getCategory()).isNotNull();
        assertThat(product.getCategory().getName()).isEqualTo("Electronics");
    }

    @Test
    void testProductChangingCategory() {
        // Arrange
        Category category1 = new Category();
        category1.setId(1L);
        category1.setName("Electronics");

        Category category2 = new Category();
        category2.setId(2L);
        category2.setName("Clothing");

        // Act
        product.setCategory(category1);
        assertThat(product.getCategory().getName()).isEqualTo("Electronics");

        product.setCategory(category2);

        // Assert
        assertThat(product.getCategory().getName()).isEqualTo("Clothing");
    }

    @Test
    void testProductWithNullCategory() {
        // Act
        product.setCategory(null);

        // Assert
        assertThat(product.getCategory()).isNull();
    }

    @Test
    void testProductIdWithZero() {
        // Act
        product.setId(0L);

        // Assert
        assertThat(product.getId()).isEqualTo(0L);
    }

    @Test
    void testProductIdWithNegativeValue() {
        // Act
        product.setId(-1L);

        // Assert
        assertThat(product.getId()).isEqualTo(-1L);
    }

    @Test
    void testAllFieldsModificationSequentially() {
        // Act
        product.setId(1L);
        assertThat(product.getId()).isEqualTo(1L);

        product.setName("Product");
        assertThat(product.getName()).isEqualTo("Product");

        product.setDescription("Description");
        assertThat(product.getDescription()).isEqualTo("Description");

        product.setPrice(100.0);
        assertThat(product.getPrice()).isEqualTo(100.0);

        product.setCategory(category);
        assertThat(product.getCategory()).isEqualTo(category);

        // Assert all values together
        assertThat(product.getId()).isEqualTo(1L);
        assertThat(product.getName()).isEqualTo("Product");
        assertThat(product.getDescription()).isEqualTo("Description");
        assertThat(product.getPrice()).isEqualTo(100.0);
        assertThat(product.getCategory()).isEqualTo(category);
    }

    @Test
    void testProductEquality() {
        // Arrange
        Product prod1 = new Product();
        prod1.setId(1L);
        prod1.setName("Laptop");
        prod1.setPrice(1000.0);

        Product prod2 = new Product();
        prod2.setId(1L);
        prod2.setName("Laptop");
        prod2.setPrice(1000.0);

        // Assert
        assertThat(prod1.getId()).isEqualTo(prod2.getId());
        assertThat(prod1.getName()).isEqualTo(prod2.getName());
        assertThat(prod1.getPrice()).isEqualTo(prod2.getPrice());
    }

    @Test
    void testProductInequality() {
        // Arrange
        Product prod1 = new Product();
        prod1.setId(1L);
        prod1.setName("Laptop");
        prod1.setPrice(1000.0);

        Product prod2 = new Product();
        prod2.setId(2L);
        prod2.setName("Mouse");
        prod2.setPrice(50.0);

        // Assert
        assertThat(prod1.getId()).isNotEqualTo(prod2.getId());
        assertThat(prod1.getName()).isNotEqualTo(prod2.getName());
        assertThat(prod1.getPrice()).isNotEqualTo(prod2.getPrice());
    }

    @Test
    void testProductComparisonByPrice() {
        // Arrange
        Product expensiveProduct = new Product();
        expensiveProduct.setName("Laptop");
        expensiveProduct.setPrice(1000.0);

        Product cheapProduct = new Product();
        cheapProduct.setName("Mouse");
        cheapProduct.setPrice(50.0);

        // Assert
        assertThat(expensiveProduct.getPrice()).isGreaterThan(cheapProduct.getPrice());
    }

    @Test
    void testProductNameWithUnicodeCharacters() {
        // Act
        product.setName("Café Table");

        // Assert
        assertThat(product.getName()).isEqualTo("Café Table");
    }

    @Test
    void testProductDescriptionWithMultilineText() {
        // Arrange
        String multilineDescription = "Line 1\nLine 2\nLine 3";

        // Act
        product.setDescription(multilineDescription);

        // Assert
        assertThat(product.getDescription()).contains("Line 1");
        assertThat(product.getDescription()).contains("Line 2");
        assertThat(product.getDescription()).contains("Line 3");
    }

    @Test
    void testProductWithDifferentPriceFormats() {
        // Test integer price
        product.setPrice(100.0);
        assertThat(product.getPrice()).isEqualTo(100.0);

        // Test decimal price
        product.setPrice(99.99);
        assertThat(product.getPrice()).isEqualTo(99.99);

        // Test very small price
        product.setPrice(0.01);
        assertThat(product.getPrice()).isEqualTo(0.01);
    }

    @Test
    void testProductCategoryNotNull() {
        // Arrange
        product.setCategory(category);

        // Act & Assert
        assertThat(product.getCategory()).isNotNull();
    }

    @Test
    void testProductWithCompletInformation() {
        // Arrange & Act
        product.setId(1L);
        product.setName("Gaming Laptop");
        product.setDescription("High-performance laptop for gaming");
        product.setPrice(1500.0);
        product.setCategory(category);

        // Assert
        assertThat(product).isNotNull();
        assertThat(product.getId()).isEqualTo(1L);
        assertThat(product.getName()).isEqualTo("Gaming Laptop");
        assertThat(product.getDescription()).isEqualTo("High-performance laptop for gaming");
        assertThat(product.getPrice()).isEqualTo(1500.0);
        assertThat(product.getCategory().getName()).isEqualTo("Electronics");
    }

    @Test
    void testProductResetFields() {
        // Arrange
        product.setId(1L);
        product.setName("Laptop");
        product.setPrice(1000.0);

        // Act
        product.setId(null);
        product.setName(null);
        product.setPrice(null);

        // Assert
        assertThat(product.getId()).isNull();
        assertThat(product.getName()).isNull();
        assertThat(product.getPrice()).isNull();
    }
}
