package com.springbootproject.product.service;

import com.springbootproject.product.dto.CategoryDTO;
import com.springbootproject.product.entity.Category;
import com.springbootproject.product.exception.CategoryAlreadyExistsException;
import com.springbootproject.product.repository.CategoryRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import org.mockito.InOrder;

@ExtendWith(MockitoExtension.class)
class CategoryServiceTest {

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryService categoryService;

    private Category category;
    private CategoryDTO categoryDTO;

    @BeforeEach
    void setUp() {
        category = new Category();
        category.setId(1L);
        category.setName("Electronics");
        categoryDTO = new CategoryDTO();
        categoryDTO.setId(1L);
        categoryDTO.setName("Electronics");
    }

    @AfterEach
    void tearDown() {
    }

    // ==================== CREATE CATEGORY TESTS ====================

    @Test
    void createCategory_categoryShouldbeCreated() {
        // Arrange
        when(categoryRepository.findByName(categoryDTO.getName())).thenReturn(Optional.empty());
        when(categoryRepository.save(any(Category.class))).thenReturn(category);

        // Act
        CategoryDTO savedCategory = categoryService.createCategory(categoryDTO);

        // Assert
        assertThat(savedCategory).isNotNull();
        assertThat(savedCategory.getId()).isEqualTo(1L);
        assertThat(savedCategory.getName()).isEqualTo("Electronics");
        verify(categoryRepository, times(1)).findByName("Electronics");
        verify(categoryRepository, times(1)).save(any(Category.class));
    }

    @Test
    void createCategory_ShouldThrowException_WhenCategoryAlreadyExists() {
        // Arrange
        when(categoryRepository.findByName(categoryDTO.getName())).thenReturn(Optional.of(category));

        // Act & Assert
        assertThatThrownBy(() -> categoryService.createCategory(categoryDTO))
                .isInstanceOf(CategoryAlreadyExistsException.class)
                .hasMessageContaining("Electronics")
                .hasMessageContaining("Already Exists");

        // Verify
        verify(categoryRepository, times(1)).findByName("Electronics");
        verify(categoryRepository, never()).save(any(Category.class));
    }

    @Test
    void createCategory_WithDifferentName() {
        // Arrange
        CategoryDTO newCategoryDTO = new CategoryDTO();
        newCategoryDTO.setName("Clothing");

        Category newCategory = new Category();
        newCategory.setId(2L);
        newCategory.setName("Clothing");

        when(categoryRepository.findByName("Clothing")).thenReturn(Optional.empty());
        when(categoryRepository.save(any(Category.class))).thenReturn(newCategory);

        // Act
        CategoryDTO result = categoryService.createCategory(newCategoryDTO);

        // Assert
        assertThat(result).isNotNull();
        assertThat(result.getName()).isEqualTo("Clothing");
        verify(categoryRepository, times(1)).findByName("Clothing");
        verify(categoryRepository, times(1)).save(any(Category.class));
    }

    @Test
    void createCategory_ValidatesMappingCorrectly() {
        // Arrange
        when(categoryRepository.findByName(categoryDTO.getName())).thenReturn(Optional.empty());
        when(categoryRepository.save(any(Category.class))).thenReturn(category);

        // Act
        CategoryDTO result = categoryService.createCategory(categoryDTO);

        // Assert
        assertThat(result)
                .isNotNull()
                .extracting("id", "name")
                .containsExactly(1L, "Electronics");
    }

    // ==================== GET ALL CATEGORIES TESTS ====================

    @Test
    void getAllCategories_Success() {
        // Arrange
        Category category2 = new Category();
        category2.setId(2L);
        category2.setName("Clothing");

        List<Category> categoryList = List.of(category, category2);
        when(categoryRepository.findAll()).thenReturn(categoryList);

        // Act
        List<CategoryDTO> result = categoryService.getAllCategories();

        // Assert
        assertThat(result)
                .isNotNull()
                .hasSize(2)
                .extracting("name")
                .containsExactly("Electronics", "Clothing");

        verify(categoryRepository, times(1)).findAll();
    }

    @Test
    void getAllCategories_EmptyList() {
        // Arrange
        when(categoryRepository.findAll()).thenReturn(List.of());

        // Act
        List<CategoryDTO> result = categoryService.getAllCategories();

        // Assert
        assertThat(result).isNotNull().isEmpty();
        verify(categoryRepository, times(1)).findAll();
    }

    @Test
    void getAllCategories_WithMultipleCategories() {
        // Arrange
        Category cat1 = new Category();
        cat1.setId(1L);
        cat1.setName("Electronics");

        Category cat2 = new Category();
        cat2.setId(2L);
        cat2.setName("Books");

        Category cat3 = new Category();
        cat3.setId(3L);
        cat3.setName("Furniture");

        List<Category> categoryList = List.of(cat1, cat2, cat3);
        when(categoryRepository.findAll()).thenReturn(categoryList);

        // Act
        List<CategoryDTO> result = categoryService.getAllCategories();

        // Assert
        assertThat(result)
                .isNotNull()
                .hasSize(3);
        assertThat(result.get(0).getName()).isEqualTo("Electronics");
        assertThat(result.get(1).getName()).isEqualTo("Books");
        assertThat(result.get(2).getName()).isEqualTo("Furniture");

        verify(categoryRepository, times(1)).findAll();
    }

    // ==================== GET CATEGORY BY ID TESTS ====================

    @Test
    void getCategoryById_Success() {
        // Arrange
        when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));

        // Act
        CategoryDTO result = categoryService.getCategoryById(1L);

        // Assert
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(1L);
        assertThat(result.getName()).isEqualTo("Electronics");

        verify(categoryRepository, times(1)).findById(1L);
    }

    @Test
    void getCategoryById_NotFound() {
        // Arrange
        when(categoryRepository.findById(1L)).thenReturn(Optional.empty());

        // Act & Assert
        assertThatThrownBy(() -> categoryService.getCategoryById(1L))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("Category Not Found");

        verify(categoryRepository, times(1)).findById(1L);
    }

    @Test
    void getCategoryById_WithDifferentId() {
        // Arrange
        Category category2 = new Category();
        category2.setId(5L);
        category2.setName("Books");

        when(categoryRepository.findById(5L)).thenReturn(Optional.of(category2));

        // Act
        CategoryDTO result = categoryService.getCategoryById(5L);

        // Assert
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(5L);
        assertThat(result.getName()).isEqualTo("Books");

        verify(categoryRepository, times(1)).findById(5L);
    }

    @Test
    void getCategoryById_VerifyMappingCorrectness() {
        // Arrange
        when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));

        // Act
        CategoryDTO result = categoryService.getCategoryById(1L);

        // Assert
        assertThat(result)
                .isNotNull()
                .extracting("id", "name")
                .containsExactly(1L, "Electronics");
    }

    // ==================== DELETE CATEGORY TESTS ====================

    @Test
    void deleteCategory_Success() {
        // Act
        String result = categoryService.deleteCategory(1L);

        // Assert
        assertThat(result)
                .isNotNull()
                .isEqualTo("YOUR CATEGORY 1 HAS BEEN SUCCESSFULLY DELETED")
                .contains("CATEGORY", "DELETED");

        verify(categoryRepository, times(1)).deleteById(1L);
    }

    @Test
    void deleteCategory_VerifyMessage() {
        // Act
        String result = categoryService.deleteCategory(5L);

        // Assert
        assertThat(result)
                .contains("5")
                .contains("HAS BEEN SUCCESSFULLY DELETED");

        verify(categoryRepository, times(1)).deleteById(5L);
    }

    @Test
    void deleteCategory_WithDifferentIds() {
        // Act
        String result1 = categoryService.deleteCategory(10L);
        String result2 = categoryService.deleteCategory(20L);

        // Assert
        assertThat(result1).contains("10");
        assertThat(result2).contains("20");

        verify(categoryRepository, times(1)).deleteById(10L);
        verify(categoryRepository, times(1)).deleteById(20L);
    }

    @Test
    void deleteCategory_VerifyRepositoryInteraction() {
        // Act
        categoryService.deleteCategory(1L);

        // Assert - Verify deleteById was called exactly once with correct ID
        verify(categoryRepository, times(1)).deleteById(1L);
        verify(categoryRepository, never()).findAll();
        verify(categoryRepository, never()).save(any());
    }

    // ==================== EDGE CASES AND VALIDATION TESTS ====================

    @Test
    void createCategory_WithNullDTO() {
        // Arrange & Act & Assert
        assertThrows(Exception.class, () -> categoryService.createCategory(null));
    }

    @Test
    void createCategory_VerifyRepositoryCallSequence() {
        // Arrange
        when(categoryRepository.findByName("Electronics")).thenReturn(Optional.empty());
        when(categoryRepository.save(any(Category.class))).thenReturn(category);

        // Act
        categoryService.createCategory(categoryDTO);

        // Assert - Verify that findByName is called before save
        InOrder inOrder = inOrder(categoryRepository);
        inOrder.verify(categoryRepository).findByName("Electronics");
        inOrder.verify(categoryRepository).save(any(Category.class));
    }

    @Test
    void getAllCategories_VerifyMapperAppliedToAllItems() {
        // Arrange
        Category cat1 = new Category();
        cat1.setId(1L);
        cat1.setName("Category1");

        Category cat2 = new Category();
        cat2.setId(2L);
        cat2.setName("Category2");

        when(categoryRepository.findAll()).thenReturn(List.of(cat1, cat2));

        // Act
        List<CategoryDTO> result = categoryService.getAllCategories();

        // Assert
        assertThat(result).hasSize(2);
        assertThat(result).allMatch(dto -> dto.getId() != null);
        assertThat(result).allMatch(dto -> dto.getName() != null);
    }

    @Test
    void deleteCategory_ReturnCorrectFormat() {
        // Act
        String result = categoryService.deleteCategory(42L);

        // Assert
        assertThat(result)
                .startsWith("YOUR CATEGORY")
                .endsWith("HAS BEEN SUCCESSFULLY DELETED")
                .contains("42");
    }
}