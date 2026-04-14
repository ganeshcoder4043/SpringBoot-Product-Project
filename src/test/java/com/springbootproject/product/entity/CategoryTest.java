package com.springbootproject.product.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class CategoryTest {

    private Category category;

    @BeforeEach
    void setUp() {
        category = new Category();
    }

    @Test
    void testCategoryConstructor() {
        // Act
        Category newCategory = new Category();

        // Assert
        assertThat(newCategory).isNotNull();
        assertThat(newCategory.getId()).isNull();
        assertThat(newCategory.getName()).isNull();
        assertThat(newCategory.getProducts()).isNotNull();
        assertThat(newCategory.getProducts()).isEmpty();
    }

    @Test
    void testSetAndGetId() {
        // Act
        category.setId(1L);

        // Assert
        assertThat(category.getId()).isEqualTo(1L);
    }

    @Test
    void testSetAndGetName() {
        // Act
        category.setName("Electronics");

        // Assert
        assertThat(category.getName()).isEqualTo("Electronics");
    }

    @Test
    void testProductsListInitialization() {
        // Act & Assert
        assertThat(category.getProducts()).isNotNull();
        assertThat(category.getProducts()).isEmpty();
        assertThat(category.getProducts()).isInstanceOf(ArrayList.class);
    }

    @Test
    void testSetAndGetProducts() {
        // Arrange
        List<Product> products = new ArrayList<>();
        Product product = new Product();
        product.setId(1L);
        product.setName("Laptop");
        products.add(product);

        // Act
        category.setProducts(products);

        // Assert
        assertThat(category.getProducts()).isNotNull();
        assertThat(category.getProducts()).hasSize(1);
        assertThat(category.getProducts().get(0).getName()).isEqualTo("Laptop");
    }

    @Test
    void testAddProductToCategory() {
        // Arrange
        category.setId(1L);
        category.setName("Electronics");

        Product product = new Product();
        product.setId(1L);
        product.setName("Laptop");
        product.setDescription("Gaming Laptop");
        product.setPrice(1000.0);
        product.setCategory(category);

        // Act
        category.getProducts().add(product);

        // Assert
        assertThat(category.getProducts()).hasSize(1);
        assertThat(category.getProducts().get(0).getName()).isEqualTo("Laptop");
        assertThat(category.getProducts().get(0).getCategory()).isEqualTo(category);
    }

    @Test
    void testAddMultipleProductsToCategory() {
        // Arrange
        category.setId(1L);
        category.setName("Electronics");

        Product product1 = new Product();
        product1.setId(1L);
        product1.setName("Laptop");
        product1.setCategory(category);

        Product product2 = new Product();
        product2.setId(2L);
        product2.setName("Mouse");
        product2.setCategory(category);

        // Act
        category.getProducts().add(product1);
        category.getProducts().add(product2);

        // Assert
        assertThat(category.getProducts()).hasSize(2);
        assertThat(category.getProducts().get(0).getName()).isEqualTo("Laptop");
        assertThat(category.getProducts().get(1).getName()).isEqualTo("Mouse");
    }

    @Test
    void testRemoveProductFromCategory() {
        // Arrange
        category.setId(1L);
        category.setName("Electronics");

        Product product = new Product();
        product.setId(1L);
        product.setName("Laptop");
        product.setCategory(category);

        category.getProducts().add(product);
        assertThat(category.getProducts()).hasSize(1);

        // Act
        category.getProducts().remove(0);

        // Assert
        assertThat(category.getProducts()).isEmpty();
    }

    @Test
    void testSetAllFields() {
        // Arrange
        Long id = 5L;
        String name = "Clothing";
        List<Product> products = new ArrayList<>();

        // Act
        category.setId(id);
        category.setName(name);
        category.setProducts(products);

        // Assert
        assertThat(category.getId()).isEqualTo(id);
        assertThat(category.getName()).isEqualTo(name);
        assertThat(category.getProducts()).isEqualTo(products);
    }

    @Test
    void testCategoryNameWithSpecialCharacters() {
        // Act
        category.setName("Electronics & Gadgets");

        // Assert
        assertThat(category.getName()).isEqualTo("Electronics & Gadgets");
    }

    @Test
    void testCategoryIdWithLargeValue() {
        // Act
        category.setId(999999999L);

        // Assert
        assertThat(category.getId()).isEqualTo(999999999L);
    }

    @Test
    void testCategoryWithEmptyName() {
        // Act
        category.setName("");

        // Assert
        assertThat(category.getName()).isEqualTo("");
    }

    @Test
    void testCategoryWithNullName() {
        // Act
        category.setName(null);

        // Assert
        assertThat(category.getName()).isNull();
    }

    @Test
    void testProductsListIsModifiable() {
        // Arrange
        Product product1 = new Product();
        product1.setName("Product1");

        Product product2 = new Product();
        product2.setName("Product2");

        // Act
        category.getProducts().add(product1);
        int sizeAfterFirstAdd = category.getProducts().size();

        category.getProducts().add(product2);
        int sizeAfterSecondAdd = category.getProducts().size();

        // Assert
        assertThat(sizeAfterFirstAdd).isEqualTo(1);
        assertThat(sizeAfterSecondAdd).isEqualTo(2);
    }

    @Test
    void testClearAllProductsFromCategory() {
        // Arrange
        Product product1 = new Product();
        product1.setName("Product1");
        Product product2 = new Product();
        product2.setName("Product2");

        category.getProducts().add(product1);
        category.getProducts().add(product2);
        assertThat(category.getProducts()).hasSize(2);

        // Act
        category.getProducts().clear();

        // Assert
        assertThat(category.getProducts()).isEmpty();
    }

    @Test
    void testCategoryDataIntegrity() {
        // Arrange
        Long id = 10L;
        String name = "Books";
        List<Product> products = new ArrayList<>();

        // Act
        category.setId(id);
        category.setName(name);
        category.setProducts(products);

        // Assert
        assertThat(category.getId()).isEqualTo(id);
        assertThat(category.getName()).isEqualTo(name);
        assertThat(category.getProducts()).isEqualTo(products);
    }

    @Test
    void testCategoryModification() {
        // Arrange
        category.setId(1L);
        category.setName("Original");

        // Act
        category.setName("Modified");

        // Assert
        assertThat(category.getId()).isEqualTo(1L);
        assertThat(category.getName()).isEqualTo("Modified");
    }

    @Test
    void testMultipleCategoryInstances() {
        // Arrange & Act
        Category category1 = new Category();
        category1.setId(1L);
        category1.setName("Electronics");

        Category category2 = new Category();
        category2.setId(2L);
        category2.setName("Clothing");

        Category category3 = new Category();
        category3.setId(3L);
        category3.setName("Books");

        // Assert
        assertThat(category1.getId()).isEqualTo(1L);
        assertThat(category2.getId()).isEqualTo(2L);
        assertThat(category3.getId()).isEqualTo(3L);
        assertThat(category1.getName()).isEqualTo("Electronics");
        assertThat(category2.getName()).isEqualTo("Clothing");
        assertThat(category3.getName()).isEqualTo("Books");
    }

    @Test
    void testProductCountInCategory() {
        // Arrange
        for (int i = 1; i <= 5; i++) {
            Product product = new Product();
            product.setId((long) i);
            product.setName("Product" + i);
            category.getProducts().add(product);
        }

        // Assert
        assertThat(category.getProducts()).hasSize(5);
    }

    @Test
    void testCategoryContainsProduct() {
        // Arrange
        Product product = new Product();
        product.setId(1L);
        product.setName("Laptop");
        category.getProducts().add(product);

        // Act & Assert
        assertThat(category.getProducts()).contains(product);
    }

    @Test
    void testCategoryDoesNotContainProduct() {
        // Arrange
        Product product1 = new Product();
        product1.setId(1L);
        product1.setName("Laptop");

        Product product2 = new Product();
        product2.setId(2L);
        product2.setName("Mouse");

        category.getProducts().add(product1);

        // Act & Assert
        assertThat(category.getProducts()).doesNotContain(product2);
    }

    @Test
    void testCategoryNameWithNumbers() {
        // Act
        category.setName("Category123");

        // Assert
        assertThat(category.getName()).isEqualTo("Category123");
    }

    @Test
    void testCategoryNameWithLongText() {
        // Arrange
        String longName = "This is a very long category name that contains multiple words";

        // Act
        category.setName(longName);

        // Assert
        assertThat(category.getName()).isEqualTo(longName);
    }

    @Test
    void testProductsListNotNull() {
        // Act & Assert
        assertThat(category.getProducts()).isNotNull();
    }

    @Test
    void testGetProductsByIndex() {
        // Arrange
        Product product1 = new Product();
        product1.setId(1L);
        product1.setName("Laptop");

        Product product2 = new Product();
        product2.setId(2L);
        product2.setName("Mouse");

        category.getProducts().add(product1);
        category.getProducts().add(product2);

        // Act & Assert
        assertThat(category.getProducts().get(0).getName()).isEqualTo("Laptop");
        assertThat(category.getProducts().get(1).getName()).isEqualTo("Mouse");
    }

    @Test
    void testCategoryIdWithZero() {
        // Act
        category.setId(0L);

        // Assert
        assertThat(category.getId()).isEqualTo(0L);
    }

    @Test
    void testCategoryIdWithNegativeValue() {
        // Act
        category.setId(-1L);

        // Assert
        assertThat(category.getId()).isEqualTo(-1L);
    }

    @Test
    void testReplaceProductsInCategory() {
        // Arrange
        Product product1 = new Product();
        product1.setName("Old Product");
        category.getProducts().add(product1);

        List<Product> newProducts = new ArrayList<>();
        Product product2 = new Product();
        product2.setName("New Product");
        newProducts.add(product2);

        // Act
        category.setProducts(newProducts);

        // Assert
        assertThat(category.getProducts()).hasSize(1);
        assertThat(category.getProducts().get(0).getName()).isEqualTo("New Product");
    }

    @Test
    void testCategoryEquality() {
        // Arrange
        Category cat1 = new Category();
        cat1.setId(1L);
        cat1.setName("Electronics");

        Category cat2 = new Category();
        cat2.setId(1L);
        cat2.setName("Electronics");

        // Assert
        assertThat(cat1.getId()).isEqualTo(cat2.getId());
        assertThat(cat1.getName()).isEqualTo(cat2.getName());
    }

    @Test
    void testCategoryInequality() {
        // Arrange
        Category cat1 = new Category();
        cat1.setId(1L);
        cat1.setName("Electronics");

        Category cat2 = new Category();
        cat2.setId(2L);
        cat2.setName("Clothing");

        // Assert
        assertThat(cat1.getId()).isNotEqualTo(cat2.getId());
        assertThat(cat1.getName()).isNotEqualTo(cat2.getName());
    }
}
