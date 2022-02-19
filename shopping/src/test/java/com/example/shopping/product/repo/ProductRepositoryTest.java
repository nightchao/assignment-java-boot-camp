package com.example.shopping.product.repo;

import com.example.shopping.product.db.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    void findByNameContainingIgnoreCase() {
        // Arrange
        List<Product> listDb = new ArrayList<>(1);
        Product product01 = new Product(4001, "Chicken 003");
        Product product02 = new Product(4002, "Kangaroo 004");
        Product product03 = new Product(4003, "Penguin 005");
        Product product04 = new Product(4004, "Elephant 006");
        Product product05 = new Product(4005, "Ant 007");
        listDb.add(product01);
        listDb.add(product02);
        listDb.add(product03);
        listDb.add(product04);
        listDb.add(product05);
        productRepository.saveAll(listDb);

        // Act
        Optional<List<Product>> result = productRepository.findByNameContainingIgnoreCase("an");

        // Assert
        assertTrue(result.isPresent());
        assertEquals(6, result.get().size());
    }

    @Test
    void findById() {
        // Arrange
        Product product = new Product(4001, "Chicken 003");
        productRepository.save(product);

        // Act
        Optional<Product> result01 = productRepository.findById(4001);
        Optional<Product> result02 = productRepository.findById(4002);

        // Assert
        assertTrue(result01.isPresent());
        assertEquals("Chicken 003", result01.get().getName());
        assertFalse(result02.isPresent());
    }
}