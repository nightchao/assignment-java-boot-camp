package com.example.shopping.product.service;

import com.example.shopping.product.db.Product;
import com.example.shopping.product.repo.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @Test
    void getListProduct() {
        // Arrange
        Product product01 = new Product(1, "Cat 008");
        Product product02 = new Product(2, "Rat 009");
        List<Product> listDb = new ArrayList<>(1);
        listDb.add(product01);
        listDb.add(product02);
        when(productRepository.findByNameContainingIgnoreCase("AT")).thenReturn(Optional.of(listDb));

        // Act
        ProductService productService = new ProductService();
        productService.setProductRepository(productRepository);
        List<Product> result = productService.getListProduct("AT");

        // Assert
        assertEquals(2, result.size());
    }

    @Test
    void getProduct() {
        // Arrange
        Product product = new Product(1, "Cat 008");
        when(productRepository.findById(1)).thenReturn(Optional.of(product));
        when(productRepository.findById(2)).thenReturn(Optional.empty());

        // Act
        ProductService productService = new ProductService();
        productService.setProductRepository(productRepository);
        Product result01 = productService.getProduct(1);
        Product result02 = productService.getProduct(2);

        // Assert
        assertEquals("Cat 008", result01.getName());
        assertNull(result02);
    }
}