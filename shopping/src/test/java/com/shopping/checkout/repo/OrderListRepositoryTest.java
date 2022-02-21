package com.shopping.checkout.repo;

import com.shopping.checkout.db.OrderList;
import com.shopping.product.db.Basket;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class OrderListRepositoryTest {

    @Autowired
    private OrderListRepository orderListRepository;

    @Test
    void findByOrderId() {
        // Arrange
        List<OrderList> listOrder = new ArrayList<>(1);
        OrderList order01 = new OrderList("order-test", 11, 22, 33, 44, null);
        OrderList order02 = new OrderList("order-test", 11, 22, 33, 44, null);
        listOrder.add(order01);
        listOrder.add(order02);
        orderListRepository.saveAll(listOrder);

        // Act
        Optional<List<OrderList>> result = orderListRepository.findByOrderId("order-test");

        // Assert
        assertTrue(result.isPresent());
        assertEquals(2, result.get().size());
    }
}