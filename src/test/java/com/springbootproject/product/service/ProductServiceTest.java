package com.springbootproject.product.service;

import com.springbootproject.product.dto.ProductDTO;
import com.springbootproject.product.entity.Category;
import com.springbootproject.product.entity.Product;
import com.springbootproject.product.exception.CategoryNotFoundException;
import com.springbootproject.product.mapper.ProductMapper;
import com.springbootproject.product.repository.CategoryRepository;
import com.springbootproject.product.repository.ProductRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @InjectMocks
    private ProductService productService;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private CategoryRepository categoryRepository;

    private ProductDTO productDTO;
    private Product product;
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
        product.setName("Test Product");
        product.setDescription("Test Description");
        product.setPrice(100.0);
        product.setCategory(category);

        // Create test product DTO
        productDTO = new ProductDTO();
        productDTO.setId(1L);
        productDTO.setName("Test Product");
        productDTO.setDescription("Test Description");
        productDTO.setPrice(100.0);
        productDTO.setCategoryId(1L);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testCreateProduct_Success() {
        // Arrange
        when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));
        when(productRepository.save(any(Product.class))).thenReturn(product);

        // Act
        ProductDTO result = productService.createProduct(productDTO);

        // Assert
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(productDTO.getId());
        assertThat(result.getName()).isEqualTo(productDTO.getName());
        assertThat(result.getDescription()).isEqualTo(productDTO.getDescription());
        assertThat(result.getPrice()).isEqualTo(productDTO.getPrice());
        assertThat(result.getCategoryId()).isEqualTo(1L);

        // Verify interactions
        verify(categoryRepository, times(1)).findById(1L);
        verify(productRepository, times(1)).save(any(Product.class));
    }

    @Test
    void testCreateProduct_CategoryNotFound() {
        // Arrange
        when(categoryRepository.findById(1L)).thenReturn(Optional.empty());

        // Act & Assert
        assertThatThrownBy(() -> productService.createProduct(productDTO))
                .isInstanceOf(CategoryNotFoundException.class)
                .hasMessageContaining("Category id 1 Not Found!!!!");

        // Verify interactions
        verify(categoryRepository, times(1)).findById(1L);
        verify(productRepository, never()).save(any(Product.class));
    }

    @Test
    void testGetAllProducts_Success() {
        // Arrange
        Product product2 = new Product();
        product2.setId(2L);
        product2.setName("Product 2");
        product2.setDescription("Description 2");
        product2.setPrice(200.0);
        product2.setCategory(category);

        List<Product> productList = List.of(product, product2);
        when(productRepository.findAll()).thenReturn(productList);

        // Act
        List<ProductDTO> result = productService.getAllProducts();

        // Assert
        assertThat(result).isNotNull();
        assertThat(result).hasSize(2);
        assertThat(result.get(0).getName()).isEqualTo(product.getName());
        assertThat(result.get(1).getName()).isEqualTo(product2.getName());

        // Verify interactions
        verify(productRepository, times(1)).findAll();
    }

    @Test
    void testGetAllProducts_EmptyList() {
        // Arrange
        when(productRepository.findAll()).thenReturn(List.of());

        // Act
        List<ProductDTO> result = productService.getAllProducts();

        // Assert
        assertThat(result).isNotNull();
        assertThat(result).isEmpty();

        // Verify interactions
        verify(productRepository, times(1)).findAll();
    }

    @Test
    void testGetProductById_Success() {
        // Arrange
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        // Act
        ProductDTO result = productService.getProductById(1L);

        // Assert
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(product.getId());
        assertThat(result.getName()).isEqualTo(product.getName());
        assertThat(result.getDescription()).isEqualTo(product.getDescription());
        assertThat(result.getPrice()).isEqualTo(product.getPrice());

        // Verify interactions
        verify(productRepository, times(1)).findById(1L);
    }

    @Test
    void testGetProductById_ProductNotFound() {
        // Arrange
        when(productRepository.findById(1L)).thenReturn(Optional.empty());

        // Act & Assert
        assertThatThrownBy(() -> productService.getProductById(1L))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("Product Not Found!!!!!!");

        // Verify interactions
        verify(productRepository, times(1)).findById(1L);
    }

    @Test
    void testUpdateProduct_Success() {
        // Arrange
        ProductDTO updateDTO = new ProductDTO();
        updateDTO.setId(1L);
        updateDTO.setName("Updated Product");
        updateDTO.setDescription("Updated Description");
        updateDTO.setPrice(150.0);
        updateDTO.setCategoryId(1L);

        Product updatedProduct = new Product();
        updatedProduct.setId(1L);
        updatedProduct.setName("Updated Product");
        updatedProduct.setDescription("Updated Description");
        updatedProduct.setPrice(150.0);
        updatedProduct.setCategory(category);

        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));
        when(productRepository.save(any(Product.class))).thenReturn(updatedProduct);

        // Act
        ProductDTO result = productService.updateProduct(1L, updateDTO);

        // Assert
        assertThat(result).isNotNull();
        assertThat(result.getName()).isEqualTo("Updated Product");
        assertThat(result.getDescription()).isEqualTo("Updated Description");
        assertThat(result.getPrice()).isEqualTo(150.0);

        // Verify interactions
        verify(productRepository, times(1)).findById(1L);
        verify(categoryRepository, times(1)).findById(1L);
        verify(productRepository, times(1)).save(any(Product.class));
    }

    @Test
    void testUpdateProduct_ProductNotFound() {
        // Arrange
        when(productRepository.findById(1L)).thenReturn(Optional.empty());

        // Act & Assert
        assertThatThrownBy(() -> productService.updateProduct(1L, productDTO))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("Product Not Found!!!!!!");

        // Verify interactions
        verify(productRepository, times(1)).findById(1L);
        verify(categoryRepository, never()).findById(any());
        verify(productRepository, never()).save(any(Product.class));
    }

    @Test
    void testUpdateProduct_CategoryNotFound() {
        // Arrange
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        when(categoryRepository.findById(1L)).thenReturn(Optional.empty());

        // Act & Assert
        assertThatThrownBy(() -> productService.updateProduct(1L, productDTO))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("Category Not Found!!!!!!");

        // Verify interactions
        verify(productRepository, times(1)).findById(1L);
        verify(categoryRepository, times(1)).findById(1L);
        verify(productRepository, never()).save(any(Product.class));
    }

    @Test
    void testDeleteProduct_Success() {
        // Arrange - no need to setup anything as delete just calls deleteById

        // Act
        String result = productService.deleteProduct(1L);

        // Assert
        assertThat(result).isNotNull();
        assertThat(result).isEqualTo("YOUR PRODUCT 1 HAS BEEN SUCCESSFULLY DELETED");

        // Verify interactions
        verify(productRepository, times(1)).deleteById(1L);
    }

    @Test
    void testDeleteProduct_VerifyMessage() {
        // Act
        String result = productService.deleteProduct(5L);

        // Assert
        assertThat(result).contains("5");
        assertThat(result).contains("HAS BEEN SUCCESSFULLY DELETED");

        // Verify interactions
        verify(productRepository, times(1)).deleteById(5L);
    }
}
