package com.shopping.checkout.repo;

import com.shopping.checkout.db.Summary;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

        System.out.println(result.isPresent());

        // Assert
        assertTrue(result.isPresent());
        assertEquals("test payer", result.get().getPayer());
        assertEquals(100, result.get().getAmount());
        assertTrue(result.get().getIsGetNews());
    }
}