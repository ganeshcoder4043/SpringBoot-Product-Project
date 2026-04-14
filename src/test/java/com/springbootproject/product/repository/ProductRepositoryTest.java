package com.springbootproject.product.repository;

import com.springbootproject.product.entity.Category;
import com.springbootproject.product.entity.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void testSaveProduct() {
        Category category = new Category();
        category.setName("Electronics");
        entityManager.persist(category);

        Product product = new Product();
        product.setName("Laptop");
        product.setDescription("A gaming laptop");
        product.setPrice(1000.0);
        product.setCategory(category);

        Product savedProduct = productRepository.save(product);

        assertThat(savedProduct.getId()).isNotNull();
        assertThat(savedProduct.getName()).isEqualTo("Laptop");
    }

    @Test
    public void testFindById() {
        Category category = new Category();
        category.setName("Electronics");
        entityManager.persist(category);

        Product product = new Product();
        product.setName("Laptop");
        product.setDescription("A gaming laptop");
        product.setPrice(1000.0);
        product.setCategory(category);

        Product savedProduct = entityManager.persistFlushFind(product);

        Optional<Product> foundProduct = productRepository.findById(savedProduct.getId());

        assertThat(foundProduct).isPresent();
        assertThat(foundProduct.get().getName()).isEqualTo("Laptop");
    }

    @Test
    public void testFindAll() {
        Category category = new Category();
        category.setName("Electronics");
        entityManager.persist(category);

        Product product1 = new Product();
        product1.setName("Laptop");
        product1.setDescription("A gaming laptop");
        product1.setPrice(1000.0);
        product1.setCategory(category);

        Product product2 = new Product();
        product2.setName("Mouse");
        product2.setDescription("Wireless mouse");
        product2.setPrice(50.0);
        product2.setCategory(category);

        productRepository.save(product1);
        productRepository.save(product2);

        List<Product> products = productRepository.findAll();

        assertThat(products).hasSizeGreaterThanOrEqualTo(2);
    }

    @Test
    public void testDeleteProduct() {
        Category category = new Category();
        category.setName("Electronics");
        entityManager.persist(category);

        Product product = new Product();
        product.setName("Laptop");
        product.setDescription("A gaming laptop");
        product.setPrice(1000.0);
        product.setCategory(category);

        Product savedProduct = productRepository.save(product);

        productRepository.delete(savedProduct);

        Optional<Product> deletedProduct = productRepository.findById(savedProduct.getId());

        assertThat(deletedProduct).isNotPresent();
    }
}
