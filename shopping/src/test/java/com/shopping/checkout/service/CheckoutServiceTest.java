package com.shopping.checkout.service;

import com.shopping.checkout.db.OrderList;
import com.shopping.checkout.repo.OrderListRepository;
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
    private OrderListRepository orderListRepository;

    @Test
    void saveOrderList() {
        // Arrange
        List<OrderList> listAllOrder = new ArrayList<>();
        String orderId = "OrderIdTest";
        OrderList orderList = new OrderList(orderId, 11, 22, 33, 100, null);
        listAllOrder.add(orderList);
        when(orderListRepository.saveAll(anyList())).thenReturn(listAllOrder);

        // Act
        CheckoutService checkoutService = new CheckoutService();
        checkoutService.setOrderListRepository(orderListRepository);
        List<OrderList> listAllOrderTest = new ArrayList<>();
        String orderIdTest = "OrderIdTest";
        OrderList orderListTest = new OrderList(orderIdTest, 11, 22, 33, 100, null);
        listAllOrderTest.add(orderListTest);
        checkoutService.saveOrderList(listAllOrderTest);

        // Assert
        assertNotNull(listAllOrderTest);
        assertEquals(1, listAllOrderTest.size());
    }

    @Test
    void getOrderById() {
        // Arrange
        OrderList orderList = new OrderList("OrderIdTest", 11, 22, 33, 44, null);
        List<OrderList> listDb = new ArrayList<>(1);
        listDb.add(orderList);
        when(orderListRepository.findByOrderId("OrderIdTest")).thenReturn(Optional.of(listDb));

        // Act
        CheckoutService checkoutService = new CheckoutService();
        checkoutService.setOrderListRepository(orderListRepository);
        List<OrderList> result = checkoutService.getOrderById("OrderIdTest");

        // Assert
        assertEquals(1, result.size());
    }
}