package com.springbootproject.product.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class ProductDTOTest {

    private ProductDTO productDTO;

    @BeforeEach
    void setUp() {
        productDTO = new ProductDTO();
    }

    @Test
    void testNoArgConstructor() {
        // Act
        ProductDTO product = new ProductDTO();

        // Assert
        assertThat(product).isNotNull();
        assertThat(product.getId()).isNull();
        assertThat(product.getName()).isNull();
        assertThat(product.getDescription()).isNull();
        assertThat(product.getPrice()).isNull();
        assertThat(product.getCategoryId()).isNull();
    }

    @Test
    void testAllArgsConstructor() {
        // Act
        ProductDTO product = new ProductDTO(1L, "Laptop", "Gaming Laptop", 1000.0, 1L);

        // Assert
        assertThat(product).isNotNull();
        assertThat(product.getId()).isEqualTo(1L);
        assertThat(product.getName()).isEqualTo("Laptop");
        assertThat(product.getDescription()).isEqualTo("Gaming Laptop");
        assertThat(product.getPrice()).isEqualTo(1000.0);
        assertThat(product.getCategoryId()).isEqualTo(1L);
    }

    @Test
    void testSetAndGetId() {
        // Act
        productDTO.setId(5L);

        // Assert
        assertThat(productDTO.getId()).isEqualTo(5L);
    }

    @Test
    void testSetAndGetName() {
        // Act
        productDTO.setName("Mouse");

        // Assert
        assertThat(productDTO.getName()).isEqualTo("Mouse");
    }

    @Test
    void testSetAndGetDescription() {
        // Act
        productDTO.setDescription("Wireless Mouse");

        // Assert
        assertThat(productDTO.getDescription()).isEqualTo("Wireless Mouse");
    }

    @Test
    void testSetAndGetPrice() {
        // Act
        productDTO.setPrice(50.0);

        // Assert
        assertThat(productDTO.getPrice()).isEqualTo(50.0);
    }

    @Test
    void testSetAndGetCategoryId() {
        // Act
        productDTO.setCategoryId(2L);

        // Assert
        assertThat(productDTO.getCategoryId()).isEqualTo(2L);
    }

    @Test
    void testSetAllFields() {
        // Arrange
        Long id = 10L;
        String name = "Monitor";
        String description = "4K Monitor";
        Double price = 300.0;
        Long categoryId = 3L;

        // Act
        productDTO.setId(id);
        productDTO.setName(name);
        productDTO.setDescription(description);
        productDTO.setPrice(price);
        productDTO.setCategoryId(categoryId);

        // Assert
        assertThat(productDTO.getId()).isEqualTo(id);
        assertThat(productDTO.getName()).isEqualTo(name);
        assertThat(productDTO.getDescription()).isEqualTo(description);
        assertThat(productDTO.getPrice()).isEqualTo(price);
        assertThat(productDTO.getCategoryId()).isEqualTo(categoryId);
    }

    @Test
    void testEqualsAndHashCode() {
        // Arrange
        ProductDTO product1 = new ProductDTO(1L, "Laptop", "Gaming Laptop", 1000.0, 1L);
        ProductDTO product2 = new ProductDTO(1L, "Laptop", "Gaming Laptop", 1000.0, 1L);

        // Assert
        assertThat(product1).isEqualTo(product2);
        assertThat(product1.hashCode()).isEqualTo(product2.hashCode());
    }

    @Test
    void testNotEquals() {
        // Arrange
        ProductDTO product1 = new ProductDTO(1L, "Laptop", "Gaming Laptop", 1000.0, 1L);
        ProductDTO product2 = new ProductDTO(2L, "Mouse", "Wireless Mouse", 50.0, 2L);

        // Assert
        assertThat(product1).isNotEqualTo(product2);
    }

    @Test
    void testNotEqualsWithDifferentPrice() {
        // Arrange
        ProductDTO product1 = new ProductDTO(1L, "Laptop", "Gaming Laptop", 1000.0, 1L);
        ProductDTO product2 = new ProductDTO(1L, "Laptop", "Gaming Laptop", 1200.0, 1L);

        // Assert
        assertThat(product1).isNotEqualTo(product2);
    }

    @Test
    void testToString() {
        // Arrange
        productDTO.setId(1L);
        productDTO.setName("Keyboard");
        productDTO.setDescription("Mechanical Keyboard");
        productDTO.setPrice(150.0);
        productDTO.setCategoryId(1L);

        // Act
        String toString = productDTO.toString();

        // Assert
        assertThat(toString).isNotNull();
        assertThat(toString).contains("id=1");
        assertThat(toString).contains("name=Keyboard");
        assertThat(toString).contains("description=Mechanical Keyboard");
        assertThat(toString).contains("price=150.0");
    }

    @Test
    void testProductNameWithSpecialCharacters() {
        // Act
        productDTO.setName("Product & Service");

        // Assert
        assertThat(productDTO.getName()).isEqualTo("Product & Service");
    }

    @Test
    void testProductDescriptionWithSpecialCharacters() {
        // Act
        productDTO.setDescription("High-quality product @ discount!");

        // Assert
        assertThat(productDTO.getDescription()).isEqualTo("High-quality product @ discount!");
    }

    @Test
    void testProductIdWithLargeValue() {
        // Act
        productDTO.setId(999999999L);

        // Assert
        assertThat(productDTO.getId()).isEqualTo(999999999L);
    }

    @Test
    void testProductPriceWithLargeValue() {
        // Act
        productDTO.setPrice(999999.99);

        // Assert
        assertThat(productDTO.getPrice()).isEqualTo(999999.99);
    }

    @Test
    void testProductPriceWithZero() {
        // Act
        productDTO.setPrice(0.0);

        // Assert
        assertThat(productDTO.getPrice()).isEqualTo(0.0);
    }

    @Test
    void testProductPriceWithNegativeValue() {
        // Act
        productDTO.setPrice(-100.0);

        // Assert
        assertThat(productDTO.getPrice()).isEqualTo(-100.0);
    }

    @Test
    void testProductWithNullValues() {
        // Act
        ProductDTO product = new ProductDTO(null, null, null, null, null);

        // Assert
        assertThat(product.getId()).isNull();
        assertThat(product.getName()).isNull();
        assertThat(product.getDescription()).isNull();
        assertThat(product.getPrice()).isNull();
        assertThat(product.getCategoryId()).isNull();
    }

    @Test
    void testProductNameWithEmptyString() {
        // Act
        productDTO.setName("");

        // Assert
        assertThat(productDTO.getName()).isEqualTo("");
    }

    @Test
    void testProductDescriptionWithLongText() {
        // Arrange
        String longDescription = "This is a very long description " +
                "that contains multiple lines and detailed information " +
                "about the product specifications and features";

        // Act
        productDTO.setDescription(longDescription);

        // Assert
        assertThat(productDTO.getDescription()).isEqualTo(longDescription);
    }

    @Test
    void testMultipleProductInstances() {
        // Arrange & Act
        ProductDTO product1 = new ProductDTO(1L, "Product1", "Desc1", 100.0, 1L);
        ProductDTO product2 = new ProductDTO(2L, "Product2", "Desc2", 200.0, 2L);
        ProductDTO product3 = new ProductDTO(3L, "Product3", "Desc3", 300.0, 3L);

        // Assert
        assertThat(product1.getId()).isEqualTo(1L);
        assertThat(product2.getId()).isEqualTo(2L);
        assertThat(product3.getId()).isEqualTo(3L);
        assertThat(product1).isNotEqualTo(product2);
        assertThat(product2).isNotEqualTo(product3);
    }

    @Test
    void testProductDataIntegrity() {
        // Arrange
        Long id = 50L;
        String name = "Headphones";
        String description = "Noise Cancelling Headphones";
        Double price = 250.0;
        Long categoryId = 5L;

        // Act
        productDTO.setId(id);
        productDTO.setName(name);
        productDTO.setDescription(description);
        productDTO.setPrice(price);
        productDTO.setCategoryId(categoryId);

        // Assert
        assertThat(productDTO.getId()).isEqualTo(id);
        assertThat(productDTO.getName()).isEqualTo(name);
        assertThat(productDTO.getDescription()).isEqualTo(description);
        assertThat(productDTO.getPrice()).isEqualTo(price);
        assertThat(productDTO.getCategoryId()).isEqualTo(categoryId);
    }

    @Test
    void testProductModification() {
        // Arrange
        ProductDTO product = new ProductDTO(1L, "Original", "Original Description", 100.0, 1L);

        // Act
        product.setName("Modified");
        product.setPrice(150.0);

        // Assert
        assertThat(product.getName()).isEqualTo("Modified");
        assertThat(product.getPrice()).isEqualTo(150.0);
        assertThat(product.getId()).isEqualTo(1L);
    }

    @Test
    void testProductWithMinimumPrice() {
        // Act
        productDTO.setPrice(0.01);

        // Assert
        assertThat(productDTO.getPrice()).isEqualTo(0.01);
    }

    @Test
    void testProductCategoryIdWithZero() {
        // Act
        productDTO.setCategoryId(0L);

        // Assert
        assertThat(productDTO.getCategoryId()).isEqualTo(0L);
    }

    @Test
    void testAllFieldsModificationSequentially() {
        // Act
        productDTO.setId(1L);
        assertThat(productDTO.getId()).isEqualTo(1L);

        productDTO.setName("Product");
        assertThat(productDTO.getName()).isEqualTo("Product");

        productDTO.setDescription("Description");
        assertThat(productDTO.getDescription()).isEqualTo("Description");

        productDTO.setPrice(100.0);
        assertThat(productDTO.getPrice()).isEqualTo(100.0);

        productDTO.setCategoryId(1L);
        assertThat(productDTO.getCategoryId()).isEqualTo(1L);

        // Assert all values together
        assertThat(productDTO.getId()).isEqualTo(1L);
        assertThat(productDTO.getName()).isEqualTo("Product");
        assertThat(productDTO.getDescription()).isEqualTo("Description");
        assertThat(productDTO.getPrice()).isEqualTo(100.0);
        assertThat(productDTO.getCategoryId()).isEqualTo(1L);
    }

    @Test
    void testProductNameWithNumbers() {
        // Act
        productDTO.setName("Product123");

        // Assert
        assertThat(productDTO.getName()).isEqualTo("Product123");
    }

    @Test
    void testProductWithDecimalPrice() {
        // Act
        productDTO.setPrice(99.99);

        // Assert
        assertThat(productDTO.getPrice()).isEqualTo(99.99);
    }
}
