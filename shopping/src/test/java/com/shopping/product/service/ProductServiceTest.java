package com.shopping.product.service;

import com.shopping.product.db.Basket;
import com.shopping.product.db.OrderList;
import com.shopping.product.db.Product;
import com.shopping.product.repo.BasketRepository;
import com.shopping.product.repo.OrderListRepository;
import com.shopping.product.repo.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private BasketRepository basketRepository;

    @Mock
    private OrderListRepository orderListRepository;

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

        // Act
        ProductService productService = new ProductService();
        productService.setBasketRepository(basketRepository);
        List<Basket> result = productService.getProductInBasket(11);

        // Assert
        assertEquals(1, result.size());
    }

    @Test
    void saveOrderList() {
        // Arrange
        List<OrderList> listAllOrder = new ArrayList<>();
        String orderId = "OrderIdTest";
        OrderList orderList = new OrderList(orderId, 11, 22, 33, 100, null);
        listAllOrder.add(orderList);
        when(orderListRepository.saveAll(anyList())).thenReturn(listAllOrder);

        // Act
        ProductService productService = new ProductService();
        productService.setOrderListRepository(orderListRepository);
        List<OrderList> listAllOrderTest = new ArrayList<>();
        String orderIdTest = "OrderIdTest";
        OrderList orderListTest = new OrderList(orderIdTest, 11, 22, 33, 100, null);
        listAllOrderTest.add(orderListTest);
        productService.saveOrderList(listAllOrderTest);

        // Assert
        assertNotNull(listAllOrderTest);
        assertEquals(1, listAllOrderTest.size());
    }
}