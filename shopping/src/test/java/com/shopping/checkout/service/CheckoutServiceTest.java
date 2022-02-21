package com.shopping.checkout.service;

import com.shopping.checkout.db.OrderBuy;
import com.shopping.checkout.repo.OrderBuyRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;

@DataJpaTest
class CheckoutServiceTest {

    @Mock
    private OrderBuyRepository orderBuyRepository;

    @Test
    void saveOrderBuy() {
        // Arrange
        List<OrderBuy> listAllOrder = new ArrayList<>();
        OrderBuy orderList = new OrderBuy("order-test-id", 11, 22, 33, 100, null);
        listAllOrder.add(orderList);
        when(orderBuyRepository.saveAll(anyList())).thenReturn(listAllOrder);

        // Act
        CheckoutService checkoutService = new CheckoutService();
        checkoutService.setOrderBuyRepository(orderBuyRepository);
        List<OrderBuy> listAllOrderTest = new ArrayList<>();
        OrderBuy orderBuyTest = new OrderBuy("order-test-id", 11, 22, 33, 100, null);
        listAllOrderTest.add(orderBuyTest);
        checkoutService.saveOrderBuy(listAllOrderTest);

        // Assert
        assertNotNull(listAllOrderTest);
        assertEquals(1, listAllOrderTest.size());
    }

    @Test
    void getOrderById() {
        // Arrange
        OrderBuy orderList = new OrderBuy("order-test-id", 11, 22, 33, 44, null);
        List<OrderBuy> listDb = new ArrayList<>(1);
        listDb.add(orderList);
        when(orderBuyRepository.findByOrderId("order-test-id")).thenReturn(Optional.of(listDb));

        // Act
        CheckoutService checkoutService = new CheckoutService();
        checkoutService.setOrderBuyRepository(orderBuyRepository);
        List<OrderBuy> result = checkoutService.getOrderById("order-test-id");

        // Assert
        assertEquals(1, result.size());
    }
}