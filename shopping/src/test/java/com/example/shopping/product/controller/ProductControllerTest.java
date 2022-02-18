package com.example.shopping.product.controller;

import com.example.shopping.product.db.Product;
import com.example.shopping.product.model.SearchReponse;
import com.example.shopping.product.repo.ProductRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProductControllerTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @MockBean
    private ProductRepository productRepository;

    @Test
    @DisplayName("เรียก /product แล้วจะต้องได้ผลการค้นหา 2 record")
    void case01() {
        // Arrange
        Product product01 = new Product(1, "testing01");
        Product product02 = new Product(2, "testing02");
        List<Product> listDb = new ArrayList<>(1);
        listDb.add(product01);
        listDb.add(product02);
        when(productRepository.findByNameContainingIgnoreCase("")).thenReturn(Optional.of(listDb));

        // Act
        SearchReponse result = testRestTemplate.getForObject("/product", SearchReponse.class);

        // Assert
        assertEquals(2, result.getTotal());
    }

    @Test
    @DisplayName("ค้นหาข้อมูลโดยใช้คำว่า FI แล้วจะต้องได้ผลการค้นหา 1 record, list search มีข้อมูล และชื่อต้องเท่ากับ Fish 007")
    void case02() {
        // Arrange
        Product product01 = new Product(1, "Fish 007");
        List<Product> listDb = new ArrayList<>(1);
        listDb.add(product01);
        when(productRepository.findByNameContainingIgnoreCase("FI")).thenReturn(Optional.of(listDb));

        // Act
        SearchReponse result = testRestTemplate.getForObject("/product?search=FI", SearchReponse.class);
        System.out.println(result.getTotal());

        // Assert
        assertEquals(1, result.getTotal());
        assertFalse(result.getListSearch().isEmpty());
        assertEquals("Fish 007", result.getListSearch().get(0).getName());
    }

    @Test
    @DisplayName("ค้นหาข้อมูลโดยใช้คำว่า OO แล้วจะต้องได้ผลการค้นหา 0 record, list search ไม่มีข้อมูล")
    void case03() {
        // Arrange
        List<Product> listDb = new ArrayList<>(1);
        when(productRepository.findByNameContainingIgnoreCase("OO")).thenReturn(Optional.of(listDb));

        // Act
        SearchReponse result = testRestTemplate.getForObject("/product?search=OO", SearchReponse.class);
        System.out.println(result.getTotal());

        // Assert
        assertEquals(0, result.getTotal());
        assertTrue(result.getListSearch().isEmpty());
    }
}