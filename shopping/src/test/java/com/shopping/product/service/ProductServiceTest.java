package com.shopping.product.service;

import com.exception.BasketItemNotFoundException;
import com.exception.ProductNotFoundException;
import com.exception.SearchNotFoundException;
import com.shopping.product.db.Basket;
import com.shopping.product.db.Product;
import com.shopping.product.repo.BasketRepository;
import com.shopping.product.repo.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private BasketRepository basketRepository;

    @Test
    void getListProduct() {
        // Arrange
        Product product01 = new Product(1, "Cat 008");
        Product product02 = new Product(2, "Rat 009");
        List<Product> listDb = new ArrayList<>(1);
        listDb.add(product01);
        listDb.add(product02);
        when(productRepository.findByNameContainingIgnoreCase("AT")).thenReturn(Optional.of(listDb));
        when(productRepository.findByNameContainingIgnoreCase("XXX")).thenReturn(Optional.empty());

        // Act
        ProductService productService = new ProductService();
        productService.setProductRepository(productRepository);
        List<Product> result = productService.getListProduct("AT");
        Exception thrown = assertThrows(SearchNotFoundException.class, () -> productService.getListProduct("XXX"));

        // Assert
        assertEquals(2, result.size());
        assertTrue(thrown.getMessage().contains("Search not found:"));
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
        Exception thrown = assertThrows(ProductNotFoundException.class, () -> productService.getProduct(2));

        // Assert
        assertEquals("Cat 008", result01.getName());
        assertTrue(thrown.getMessage().contains("productId"));
    }

    @Test
    void saveBasket() {
        // Arrange
        Basket basket = new Basket(11, 22);
        basket.setQuantity(33);
        when(basketRepository.save(any(Basket.class))).thenReturn(basket);

        // Act
        ProductService productService = new ProductService();
        productService.setBasketRepository(basketRepository);
        Basket basketTest = new Basket(11, 22);
        basketTest.setQuantity(33);
        productService.saveBasket(basketTest);

        // Assert
        assertNotNull(basketTest);
        assertEquals(11, basketTest.getUserId());
        assertEquals(22, basketTest.getProductId());
        assertEquals(33, basketTest.getQuantity());
    }

    @Test
    void getProductInBasket() {
        // Arrange
        Basket basket = new Basket(11, 22);
        basket.setQuantity(1);
        basket.setSize(42);
        basket.setImage("https://image/1.jpg");
        List<Basket> listDb = new ArrayList<>(1);
        listDb.add(basket);
        when(basketRepository.findByUserId(11)).thenReturn(Optional.of(listDb));
        when(basketRepository.findByUserId(22)).thenReturn(Optional.empty());
        when(basketRepository.findByUserId(33)).thenReturn(Optional.of(new ArrayList<>(1)));

        // Act
        ProductService productService = new ProductService();
        productService.setBasketRepository(basketRepository);
        List<Basket> result = productService.getProductInBasket(11);
        Exception thrown01 = assertThrows(BasketItemNotFoundException.class, () -> productService.getProductInBasket(22));
        Exception thrown02 = assertThrows(BasketItemNotFoundException.class, () -> productService.getProductInBasket(33));


        // Assert
        assertEquals(1, result.size());
        assertTrue(thrown01.getMessage().contains("Product in basket not found userId:"));
        assertTrue(thrown02.getMessage().contains("Product in basket not found userId:"));
    }

    @Test
    void priceFinal() {
        // Arrange
        int price = 10000;
        int discount = 10;
        Integer vat = 7;

        // Act
        ProductService productService = new ProductService();
        int result01 = productService.priceFinal(price, discount, vat);
        int result02 = productService.priceFinal(price, discount, null);

        // Assert
        assertEquals(9630, result01);
        assertEquals(9000, result02);
    }
}