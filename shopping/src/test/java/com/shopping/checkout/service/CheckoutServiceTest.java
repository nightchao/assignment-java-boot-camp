package com.shopping.checkout.service;

import com.exception.OrderNotFoundException;
import com.exception.PaymentNotFoundException;
import com.shopping.checkout.db.OrderBuy;
import com.shopping.checkout.db.Payment;
import com.shopping.checkout.repo.OrderBuyRepository;
import com.shopping.checkout.repo.PaymentRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;

@DataJpaTest
class CheckoutServiceTest {

    @Mock
    private OrderBuyRepository orderBuyRepository;

    @Mock
    private PaymentRepository paymentRepository;

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
        when(orderBuyRepository.findByOrderId("order-test-id-e")).thenReturn(Optional.empty());


        // Act
        CheckoutService checkoutService = new CheckoutService();
        checkoutService.setOrderBuyRepository(orderBuyRepository);
        List<OrderBuy> result = checkoutService.getOrderById("order-test-id");
        Exception thrown = assertThrows(OrderNotFoundException.class, () -> checkoutService.getOrderById("order-test-id-e"));

        // Assert
        assertEquals(1, result.size());
        assertTrue(thrown.getMessage().contains("Order id:"));
    }

    @Test
    void getAllPayment() {
        // Arrange
        Payment payment01 = new Payment(1, "A Method");
        Payment payment02 = new Payment(2, "B Method");
        Payment payment03 = new Payment(3, "C Method");
        List<Payment> listDb = new ArrayList<>(1);
        listDb.add(payment01);
        listDb.add(payment02);
        listDb.add(payment03);
        when(paymentRepository.findAll()).thenReturn(listDb);

        // Act
        CheckoutService checkoutService = new CheckoutService();
        checkoutService.setPaymentRepository(paymentRepository);
        List<Payment> result = checkoutService.getAllPayment();

        // Assert
        assertEquals(3, result.size());
    }

    @Test
    void getAllPaymentCaseNoData() {
        // Arrange
        when(paymentRepository.findAll()).thenReturn(new ArrayList<>(1));

        // Act
        CheckoutService checkoutService = new CheckoutService();
        checkoutService.setPaymentRepository(paymentRepository);
        Exception thrown = assertThrows(PaymentNotFoundException.class, checkoutService::getAllPayment);

        // Assert
        assertTrue(thrown.getMessage().contains("Payment method not found"));
    }
}