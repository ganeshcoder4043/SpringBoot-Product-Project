package com.springbootproject.product.repository;

import com.springbootproject.product.entity.Category;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CategoryRepositoryTest {

    @Autowired
    private CategoryRepository categoryRepository;

    private Category category;

    @BeforeEach
    void setUp() {
        category = new Category();
        category.setName("test");
        categoryRepository.save(category);
    }

    @AfterEach
    void tearDown() {
        categoryRepository.delete(category);
    }

    @Test
    void findByName() {
        Category foundCategory = categoryRepository.findByName("test").orElse(null);
        assertNotNull(foundCategory);
        assertEquals(category.getName(), foundCategory.getName());
    }

    @Test
    void deleteByName() {
        categoryRepository.deleteByName("test");
        Category foundCategory = categoryRepository.findByName("test").orElse(null);
        assertNull(foundCategory);
    }
}