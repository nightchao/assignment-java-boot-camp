package com.shopping.checkout.service;

import com.exception.OrderNotFoundException;
import com.exception.PaymentNotFoundException;
import com.exception.SummaryNotFoundException;
import com.shopping.checkout.db.OrderBuy;
import com.shopping.checkout.db.Payment;
import com.shopping.checkout.db.Summary;
import com.shopping.checkout.repo.OrderBuyRepository;
import com.shopping.checkout.repo.PaymentRepository;
import com.shopping.checkout.repo.SummaryRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;

@DataJpaTest
class CheckoutServiceTest {

    @Mock
    private OrderBuyRepository orderBuyRepository;

    @Mock
    private PaymentRepository paymentRepository;

    @Mock
    private SummaryRepository summaryRepository;

    @Test
    void saveOrderBuy() {
        // Arrange
        List<OrderBuy> listAllOrder = new ArrayList<>();
        OrderBuy orderList = new OrderBuy("order-test-id", 11, 22, 33, 100);
        listAllOrder.add(orderList);
        when(orderBuyRepository.saveAll(anyList())).thenReturn(listAllOrder);

        // Act
        CheckoutService checkoutService = new CheckoutService();
        checkoutService.setOrderBuyRepository(orderBuyRepository);
        List<OrderBuy> listAllOrderTest = new ArrayList<>();
        OrderBuy orderBuyTest = new OrderBuy("order-test-id", 11, 22, 33, 100);
        listAllOrderTest.add(orderBuyTest);
        checkoutService.saveOrderBuy(listAllOrderTest);

        // Assert
        assertNotNull(listAllOrderTest);
        assertEquals(1, listAllOrderTest.size());
    }

    @Test
    void getOrderById() {
        // Arrange
        OrderBuy orderList = new OrderBuy("order-test-id", 11, 22, 33, 44);
        List<OrderBuy> listDb = new ArrayList<>(1);
        listDb.add(orderList);
        when(orderBuyRepository.findByOrderId("order-test-id")).thenReturn(Optional.of(listDb));
        when(orderBuyRepository.findByOrderId("order-test-id-e")).thenReturn(Optional.empty());
        when(orderBuyRepository.findByOrderId("order-test-id-f")).thenReturn(Optional.of(new ArrayList<>(1)));

        // Act
        CheckoutService checkoutService = new CheckoutService();
        checkoutService.setOrderBuyRepository(orderBuyRepository);
        List<OrderBuy> result = checkoutService.getOrderById("order-test-id");
        Exception thrown01 = assertThrows(OrderNotFoundException.class, () -> checkoutService.getOrderById("order-test-id-e"));
        Exception thrown02 = assertThrows(OrderNotFoundException.class, () -> checkoutService.getOrderById("order-test-id-f"));

        // Assert
        assertEquals(1, result.size());
        assertTrue(thrown01.getMessage().contains("Order id:"));
        assertTrue(thrown02.getMessage().contains("Order id:"));
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

    private void initDataSummary(Summary summary) {
        summary.setInvoiceNo(1);
        summary.setOrderId("order-test-id");
        summary.setPayer("test payer");
        summary.setTransactionDate(new Date());
        summary.setExpiredDate(new Date());
        summary.setPayee("test payee");
        summary.setDetail("test detail");
        summary.setAmount(100);
        summary.setPaymentMethodId(111);
        summary.setIsReceiptVat(true);
        summary.setIsGetNews(true);
    }

    @Test
    void saveSummary() {
        // Arrange
        Summary summary = new Summary();
        initDataSummary(summary);
        when(summaryRepository.save(any(Summary.class))).thenReturn(summary);

        // Act
        CheckoutService checkoutService = new CheckoutService();
        checkoutService.setSummaryRepository(summaryRepository);
        Summary summaryTest = new Summary();
        summaryTest.setInvoiceNo(1);
        summaryTest.setOrderId("order-test-id");
        summaryTest.setPayer("test payer");
        summaryTest.setTransactionDate(new Date());
        summaryTest.setExpiredDate(new Date());
        summaryTest.setPayee("test payee");
        summaryTest.setDetail("test detail");
        summaryTest.setAmount(100);
        summaryTest.setPaymentMethodId(111);
        summaryTest.setIsReceiptVat(true);
        summaryTest.setIsGetNews(true);
        checkoutService.saveSummary(summaryTest);

        // Assert
        assertNotNull(summaryTest);
        assertEquals(1, summary.getInvoiceNo());
        assertEquals("test payer", summaryTest.getPayer());
        assertEquals(111, summaryTest.getPaymentMethodId());
        assertTrue(summaryTest.getIsGetNews());
    }

    @Test
    void getSummary() {
        // Arrange
        Summary summary = new Summary();
        initDataSummary(summary);
        when(summaryRepository.findById(1)).thenReturn(Optional.of(summary));
        when(summaryRepository.findById(2)).thenReturn(Optional.empty());

        // Act
        CheckoutService checkoutService = new CheckoutService();
        checkoutService.setSummaryRepository(summaryRepository);
        Summary result = checkoutService.getSummary(1);
        Exception thrown = assertThrows(SummaryNotFoundException.class, () -> checkoutService.getSummary(2));

        // Assert
        assertNotNull(result);
        assertEquals("test payer", result.getPayer());
        assertEquals(111, result.getPaymentMethodId());
        assertTrue(result.getIsGetNews());
        assertTrue(thrown.getMessage().contains("Summary:"));
    }
}