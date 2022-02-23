package com.shopping.product.controller;

import com.exception.ExceptionModel;
import com.shopping.product.db.Basket;
import com.shopping.product.db.Product;
import com.shopping.product.model.*;
import com.shopping.product.repo.BasketRepository;
import com.shopping.product.repo.ProductRepository;
import com.user.db.ScmUser;
import com.user.repo.UserRepository;
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

    @MockBean
    private BasketRepository basketRepository;

    @Test
    @DisplayName("ค้นหาข้อมูลทดสอบโดยไม่ส่งคำค้นหา แล้วได้ผลการค้นหา 2 record")
    void case01() {
        // Arrange
        Product product01 = new Product(1, "test01");
        Product product02 = new Product(2, "test02");
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
    @DisplayName("ค้นหาข้อมูลทดสอบโดยใช้คำว่า te แล้วได้ผลการค้นหา 1 record, list search มีข้อมูล และชื่อสินค้าต้องเท่ากับ Test 007")
    void case02() {
        // Arrange
        Product product01 = new Product(1, "Test 007");
        List<Product> listDb = new ArrayList<>(1);
        listDb.add(product01);
        when(productRepository.findByNameContainingIgnoreCase("te")).thenReturn(Optional.of(listDb));

        // Act
        SearchResponse result = testRestTemplate.getForObject("/product?search=te", SearchResponse.class);

        // Assert
        assertEquals(1, result.getTotal());
        assertFalse(result.getListSearch().isEmpty());
        assertEquals("Test 007", result.getListSearch().get(0).getName());
    }

    @Test
    @DisplayName("ค้นหาข้อมูลทดสอบโดยใช้คำว่า test แล้วได้รับ JSON Object error กรณี Search not found")
    void case03() {
        // Arrange
        when(productRepository.findByNameContainingIgnoreCase("test")).thenReturn(Optional.empty());

        // Act
        ExceptionModel exception = testRestTemplate.getForObject("/product?search=test", ExceptionModel.class);

        // Assert
        String expectedMessage = "Search not found: test";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
        assertThat(exception.getStatus(), equalTo(HttpStatus.NOT_FOUND.value()));
    }

    @Test
    @DisplayName("ข้อมูลทดสอบรายละเอียดสินค้าโดยส่งตัวแปร productId = 1 แล้วเจอสินค้าที่ชื่อว่า test product")
    void case04() {
        // Arrange
        Product product = new Product(1, "test product");
        when(productRepository.findById(1)).thenReturn(Optional.of(product));

        // Act
        DetailResponse result = testRestTemplate.getForObject("/product/1", DetailResponse.class);

        // Assert
        assertEquals("test product", result.getName());
    }

    @Test()
    @DisplayName("ข้อมูลทดสอบรายละเอียดสินค้าโดยส่งตัวแปร productId = 2 แล้วได้รับ JSON Object error กรณี Product not found")
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
    @DisplayName("เพิ่มข้อมูลทดสอบสินค้าลงตะกร้าโดยส่งตัวแปรดังนี้ userId = 11, productId = 22, quantity = 33 แล้วได้รับ message = Update Success")
    void case06() {
        // Arrange
        ScmUser user = new ScmUser(11, "test fullName");
        when(userRepository.findById(11)).thenReturn(Optional.of(user));

        Product product = new Product(22, "test product");
        product.setQuantity(33);
        when(productRepository.findById(22)).thenReturn(Optional.of(product));

        AddBasketRequest addBasketRequest = new AddBasketRequest(11, 22, 33);

        // Act
        AddBasketResponse result = testRestTemplate.postForObject("/product/basket", addBasketRequest, AddBasketResponse.class);

        // Assert
        assertEquals("Update Success", result.getMessage());
    }

    @Test
    @DisplayName("เพิ่มข้อมูลทดสอบสินค้าลงตะกร้าโดยส่งตัวแปรดังนี้ userId = 11, productId = 22, quantity = 33 แล้วได้รับ JSON Object error กรณี User not found")
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
    @DisplayName("เพิ่มข้อมูลทดสอบสินค้าลงตะกร้าโดยส่งตัวแปรดังนี้ userId = 11, productId = 22, quantity = 33 แล้วได้รับ JSON Object error กรณี Product not found")
    void case08() {
        // Arrange
        ScmUser user = new ScmUser(11, "test fullName");
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

    @Test
    @DisplayName("แสดงข้อมูลทดสอบสินค้าลงตะกร้าโดยส่งตัวแปร userId = 11 แล้วผลลัพธ์มีสินค้าในตะกร้า 1 รายการ")
    void case09() {
        // Arrange
        List<Basket> listBasketItem = new ArrayList<>(1);
        Basket basket = new Basket(11, 22);
        basket.setQuantity(1);
        basket.setSize(42);
        basket.setImage("https://image/1.jpg");
        listBasketItem.add(basket);
        when(basketRepository.findByUserId(11)).thenReturn(Optional.of(listBasketItem));

        Product product = new Product(22, "test product");
        product.setQuantity(33);
        when(productRepository.findById(22)).thenReturn(Optional.of(product));

        // Act
        GetBasketResponse result = testRestTemplate.getForObject("/product/basket/11", GetBasketResponse.class);

        // Assert
        assertEquals(1, result.getTotal());
    }

    @Test
    @DisplayName("ทำการ checkout ข้อมูลทดสอบสินค้าโดยส่ง userId = 11 แล้วมีค่า orderId กลับมา")
    void case10() {
        // Arrange
        List<Basket> listBasketItem = new ArrayList<>(1);
        Basket basket = new Basket(11, 22);
        basket.setQuantity(1);
        basket.setSize(42);
        basket.setImage("https://image/1.jpg");
        listBasketItem.add(basket);
        when(basketRepository.findByUserId(11)).thenReturn(Optional.of(listBasketItem));

        Product product = new Product(22, "product name");
        product.setQuantity(33);
        when(productRepository.findById(22)).thenReturn(Optional.of(product));

        CheckOutRequest checkOutRequest = new CheckOutRequest(11);

        // Act
        CheckOutResponse result = testRestTemplate.postForObject("/product/basket/checkout", checkOutRequest, CheckOutResponse.class);

        // Assert
        assertEquals("Update Success", result.getMessage());
        assertNotNull(result.getOrderId());
    }

    @Test
    @DisplayName("ทำการ checkout ข้อมูลทดสอบสินค้าโดยส่ง userId = 11 แล้วได้รับ JSON Object error กรณีไม่มีสินค้าในตะกร้า")
    void case11() {
        // Arrange
        when(basketRepository.findByUserId(11)).thenReturn(Optional.empty());

        CheckOutRequest checkOutRequest = new CheckOutRequest(11);

        // Act
        ExceptionModel exception = testRestTemplate.postForObject("/product/basket/checkout", checkOutRequest, ExceptionModel.class);

        // Assert
        String expectedMessage = "Product in basket not found userId: 11";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
        assertThat(exception.getStatus(), equalTo(HttpStatus.NOT_FOUND.value()));
    }
}