package com.shopping.checkout.repo;

import com.shopping.checkout.db.Summary;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class SummaryRepositoryTest {

    @Autowired
    private SummaryRepository summaryRepository;

    @Test
    void findById() {
        // Arrange
        Summary summary = new Summary();
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
        summaryRepository.save(summary);

        // Act
        Optional<Summary> result = summaryRepository.findById(summary.getInvoiceNo());

        // Assert
        assertTrue(result.isPresent());
        assertEquals("order-test-id", result.get().getOrderId());
        assertEquals("test payer", result.get().getPayer());
        assertNotNull(result.get().getTransactionDate());
        assertNotNull(result.get().getExpiredDate());
        assertEquals("test payee", result.get().getPayee());
        assertEquals("test detail", result.get().getDetail());
        assertEquals(100, result.get().getAmount());
        assertEquals(111, result.get().getPaymentMethodId());
        assertTrue(result.get().getIsReceiptVat());
        assertTrue(result.get().getIsGetNews());
    }
}