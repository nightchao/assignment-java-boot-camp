package com.example.shopping.product.controller;

import com.example.shopping.product.db.Product;
import com.example.shopping.product.db.ScmUser;
import com.example.shopping.product.exception.ExceptionModel;
import com.example.shopping.product.model.AddBasketRequest;
import com.example.shopping.product.model.AddBasketResponse;
import com.example.shopping.product.model.DetailResponse;
import com.example.shopping.product.model.SearchResponse;
import com.example.shopping.product.repo.ProductRepository;
import com.example.shopping.product.repo.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProductControllerTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @MockBean
    private ProductRepository productRepository;

    @MockBean
    private UserRepository userRepository;

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
        SearchResponse result = testRestTemplate.getForObject("/product", SearchResponse.class);

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
        SearchResponse result = testRestTemplate.getForObject("/product?search=FI", SearchResponse.class);

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
        SearchResponse result = testRestTemplate.getForObject("/product?search=OO", SearchResponse.class);

        // Assert
        assertEquals(0, result.getTotal());
        assertTrue(result.getListSearch().isEmpty());
    }

    @Test
    @DisplayName("ค้นหาข้อมูลรายละเอียดสินค้าโดยส่ง productId = 1 แล้วเจอสินค้าที่ชื่อว่า test name")
    void case04() {
        // Arrange
        Product product = new Product(1, "test name");
        when(productRepository.findById(1)).thenReturn(Optional.of(product));

        // Act
        DetailResponse result = testRestTemplate.getForObject("/product/1", DetailResponse.class);

        // Assert
        assertEquals("test name", result.getName());
    }

    @Test()
    @DisplayName("ค้นหาข้อมูลรายละเอียดสินค้าโดยส่ง productId = 2 แล้วเจอไม่พบสินค้าจากนั้นส่ง JSON Object error กลับมา")
    void case05() {
        // Arrange
        when(productRepository.findById(2)).thenReturn(Optional.empty());

        // Act
        ExceptionModel exception = testRestTemplate.getForObject("/product/2", ExceptionModel.class);

        // Assert
        String expectedMessage = "productId 2 not found";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
        assertThat(exception.getStatus(), equalTo(HttpStatus.NOT_FOUND.value()));
    }

    @Test
    @DisplayName("userId = 11, productId = 22, quantity = 33 แล้วได้รับ message = Update Success")
    void case06() {
        // Arrange
        ScmUser user = new ScmUser(11, "user name");
        when(userRepository.findById(11)).thenReturn(Optional.of(user));

        Product product = new Product(22, "product name");
        product.setQuantity(33);
        when(productRepository.findById(22)).thenReturn(Optional.of(product));

        AddBasketRequest addBasketRequest = new AddBasketRequest(11, 22, 33);

        // Act
        AddBasketResponse result = testRestTemplate.postForObject("/product/basket", addBasketRequest, AddBasketResponse.class);

        // Assert
        assertEquals("Update Success", result.getMessage());
    }

    @Test
    @DisplayName("userId = 11, productId = 22, quantity = 33 แล้วได้รับ JSON Object error กรณี User not found")
    void case07() {
        // Arrange
        when(userRepository.findById(11)).thenReturn(Optional.empty());

        AddBasketRequest addBasketRequest = new AddBasketRequest(11, 22, 33);

        // Act
        ExceptionModel exception = testRestTemplate.postForObject("/product/basket", addBasketRequest, ExceptionModel.class);

        // Assert
        String expectedMessage = "User id: 11 not found";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
        assertThat(exception.getStatus(), equalTo(HttpStatus.NOT_FOUND.value()));
    }

    @Test
    @DisplayName("userId = 11, productId = 22, quantity = 33 แล้วได้รับ JSON Object error กรณี Product not found")
    void case08() {
        // Arrange
        ScmUser user = new ScmUser(11, "user name");
        when(userRepository.findById(11)).thenReturn(Optional.of(user));

        when(productRepository.findById(22)).thenReturn(Optional.empty());

        AddBasketRequest addBasketRequest = new AddBasketRequest(11, 22, 33);

        // Act
        ExceptionModel exception = testRestTemplate.postForObject("/product/basket", addBasketRequest, ExceptionModel.class);

        // Assert
        String expectedMessage = "productId 22 not found";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
        assertThat(exception.getStatus(), equalTo(HttpStatus.NOT_FOUND.value()));
    }
}