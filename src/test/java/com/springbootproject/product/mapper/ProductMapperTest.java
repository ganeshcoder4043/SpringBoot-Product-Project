package com.springbootproject.product.mapper;

import com.springbootproject.product.dto.ProductDTO;
import com.springbootproject.product.entity.Category;
import com.springbootproject.product.entity.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class ProductMapperTest {

    private Product product;
    private ProductDTO productDTO;
    private Category category;

    @BeforeEach
    void setUp() {
        // Create test category
        category = new Category();
        category.setId(1L);
        category.setName("Electronics");

        // Create test product
        product = new Product();
        product.setId(1L);
        product.setName("Laptop");
        product.setDescription("Gaming Laptop");
        product.setPrice(1000.0);
        product.setCategory(category);

        // Create test product DTO
        productDTO = new ProductDTO();
        productDTO.setId(1L);
        productDTO.setName("Laptop");
        productDTO.setDescription("Gaming Laptop");
        productDTO.setPrice(1000.0);
        productDTO.setCategoryId(1L);
    }

    @Test
    void testToProductDTO() {
        // Act
        ProductDTO result = ProductMapper.toProductDTO(product);

        // Assert
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(product.getId());
        assertThat(result.getName()).isEqualTo(product.getName());
        assertThat(result.getDescription()).isEqualTo(product.getDescription());
        assertThat(result.getPrice()).isEqualTo(product.getPrice());
        assertThat(result.getCategoryId()).isEqualTo(category.getId());
    }

    @Test
    void testToProductDTOWithAllFields() {
        // Arrange
        product.setName("Mouse");
        product.setDescription("Wireless Mouse");
        product.setPrice(50.0);

        // Act
        ProductDTO result = ProductMapper.toProductDTO(product);

        // Assert
        assertThat(result.getName()).isEqualTo("Mouse");
        assertThat(result.getDescription()).isEqualTo("Wireless Mouse");
        assertThat(result.getPrice()).isEqualTo(50.0);
    }

    @Test
    void testToProductEntity() {
        // Act
        Product result = ProductMapper.toProductEntity(productDTO, category);

        // Assert
        assertThat(result).isNotNull();
        assertThat(result.getName()).isEqualTo(productDTO.getName());
        assertThat(result.getDescription()).isEqualTo(productDTO.getDescription());
        assertThat(result.getPrice()).isEqualTo(productDTO.getPrice());
        assertThat(result.getCategory()).isEqualTo(category);
    }

    @Test
    void testToProductEntityWithDifferentCategory() {
        // Arrange
        Category category2 = new Category();
        category2.setId(2L);
        category2.setName("Clothing");

        // Act
        Product result = ProductMapper.toProductEntity(productDTO, category2);

        // Assert
        assertThat(result.getCategory()).isEqualTo(category2);
        assertThat(result.getCategory().getId()).isEqualTo(2L);
    }

    @Test
    void testProductDTOToEntityMapping() {
        // Arrange
        ProductDTO dto = new ProductDTO(2L, "Keyboard", "Mechanical", 150.0, 1L);

        // Act
        Product entity = ProductMapper.toProductEntity(dto, category);

        // Assert
        assertThat(entity.getId()).isNull(); // DTO id is not transferred to entity
        assertThat(entity.getName()).isEqualTo(dto.getName());
        assertThat(entity.getDescription()).isEqualTo(dto.getDescription());
        assertThat(entity.getPrice()).isEqualTo(dto.getPrice());
    }

    @Test
    void testProductEntityToDTOMapping() {
        // Arrange
        Product prod = new Product();
        prod.setId(5L);
        prod.setName("Monitor");
        prod.setDescription("4K Monitor");
        prod.setPrice(300.0);
        prod.setCategory(category);

        // Act
        ProductDTO dto = ProductMapper.toProductDTO(prod);

        // Assert
        assertThat(dto.getId()).isEqualTo(5L);
        assertThat(dto.getName()).isEqualTo("Monitor");
        assertThat(dto.getDescription()).isEqualTo("4K Monitor");
        assertThat(dto.getPrice()).isEqualTo(300.0);
        assertThat(dto.getCategoryId()).isEqualTo(1L);
    }

    @Test
    void testToProductEntityPreservesAllFields() {
        // Arrange
        productDTO.setName("Speaker");
        productDTO.setDescription("Bluetooth Speaker");
        productDTO.setPrice(80.0);

        // Act
        Product result = ProductMapper.toProductEntity(productDTO, category);

        // Assert
        assertThat(result.getName()).isEqualTo("Speaker");
        assertThat(result.getDescription()).isEqualTo("Bluetooth Speaker");
        assertThat(result.getPrice()).isEqualTo(80.0);
        assertThat(result.getCategory()).isNotNull();
    }

    @Test
    void testToProductDTOWithNullableFields() {
        // Arrange
        Product prodWithNulls = new Product();
        prodWithNulls.setId(1L);
        prodWithNulls.setCategory(category);

        // Act
        ProductDTO result = ProductMapper.toProductDTO(prodWithNulls);

        // Assert
        assertThat(result.getId()).isEqualTo(1L);
        assertThat(result.getName()).isNull();
        assertThat(result.getDescription()).isNull();
        assertThat(result.getPrice()).isNull();
    }

    @Test
    void testRoundTripConversionEntityToDTOToEntity() {
        // Arrange
        Product originalProduct = new Product();
        originalProduct.setId(1L);
        originalProduct.setName("Headphones");
        originalProduct.setDescription("Noise Cancelling");
        originalProduct.setPrice(200.0);
        originalProduct.setCategory(category);

        // Act - Entity to DTO
        ProductDTO dto = ProductMapper.toProductDTO(originalProduct);

        // Act - DTO to Entity
        Product convertedProduct = ProductMapper.toProductEntity(dto, category);

        // Assert
        assertThat(convertedProduct.getName()).isEqualTo(originalProduct.getName());
        assertThat(convertedProduct.getDescription()).isEqualTo(originalProduct.getDescription());
        assertThat(convertedProduct.getPrice()).isEqualTo(originalProduct.getPrice());
        assertThat(convertedProduct.getCategory()).isEqualTo(originalProduct.getCategory());
    }

    @Test
    void testProductDTOWithSpecialCharactersInName() {
        // Arrange
        ProductDTO dto = new ProductDTO(1L, "Product & Service", "Test", 100.0, 1L);

        // Act
        Product entity = ProductMapper.toProductEntity(dto, category);

        // Assert
        assertThat(entity.getName()).isEqualTo("Product & Service");
    }

    @Test
    void testProductEntityWithLargePrice() {
        // Arrange
        product.setPrice(999999.99);

        // Act
        ProductDTO dto = ProductMapper.toProductDTO(product);

        // Assert
        assertThat(dto.getPrice()).isEqualTo(999999.99);
    }

    @Test
    void testProductEntityWithZeroPrice() {
        // Arrange
        product.setPrice(0.0);

        // Act
        ProductDTO dto = ProductMapper.toProductDTO(product);

        // Assert
        assertThat(dto.getPrice()).isEqualTo(0.0);
    }

    @Test
    void testProductEntityWithNegativePrice() {
        // Arrange
        product.setPrice(-100.0);

        // Act
        ProductDTO dto = ProductMapper.toProductDTO(product);

        // Assert
        assertThat(dto.getPrice()).isEqualTo(-100.0);
    }

    @Test
    void testProductDTOWithDecimalPrice() {
        // Arrange
        productDTO.setPrice(99.99);

        // Act
        Product entity = ProductMapper.toProductEntity(productDTO, category);

        // Assert
        assertThat(entity.getPrice()).isEqualTo(99.99);
    }

    @Test
    void testCategoryMappingIsCorrect() {
        // Arrange
        Category newCategory = new Category();
        newCategory.setId(5L);
        newCategory.setName("Books");

        // Act
        Product result = ProductMapper.toProductEntity(productDTO, newCategory);

        // Assert
        assertThat(result.getCategory().getId()).isEqualTo(5L);
        assertThat(result.getCategory().getName()).isEqualTo("Books");
    }

    @Test
    void testMultipleProductsMappingToDTO() {
        // Arrange
        Product prod1 = new Product();
        prod1.setId(1L);
        prod1.setName("Product1");
        prod1.setCategory(category);

        Product prod2 = new Product();
        prod2.setId(2L);
        prod2.setName("Product2");
        prod2.setCategory(category);

        // Act
        ProductDTO dto1 = ProductMapper.toProductDTO(prod1);
        ProductDTO dto2 = ProductMapper.toProductDTO(prod2);

        // Assert
        assertThat(dto1.getId()).isEqualTo(1L);
        assertThat(dto2.getId()).isEqualTo(2L);
        assertThat(dto1.getName()).isNotEqualTo(dto2.getName());
    }

    @Test
    void testProductMapperFieldConsistency() {
        // Arrange
        ProductDTO originalDTO = new ProductDTO(1L, "Test", "Test Description", 123.45, 1L);

        // Act
        Product entity = ProductMapper.toProductEntity(originalDTO, category);
        ProductDTO resultDTO = ProductMapper.toProductDTO(entity);

        // Assert
        assertThat(resultDTO.getName()).isEqualTo(originalDTO.getName());
        assertThat(resultDTO.getDescription()).isEqualTo(originalDTO.getDescription());
        assertThat(resultDTO.getPrice()).isEqualTo(originalDTO.getPrice());
        assertThat(resultDTO.getCategoryId()).isEqualTo(originalDTO.getCategoryId());
    }
}
