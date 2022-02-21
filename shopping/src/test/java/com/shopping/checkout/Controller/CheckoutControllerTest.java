package com.shopping.checkout.Controller;

import com.exception.ExceptionModel;
import com.shopping.checkout.db.OrderBuy;
import com.shopping.checkout.model.ShippingResponse;
import com.shopping.checkout.repo.OrderBuyRepository;
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
class CheckoutControllerTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @MockBean
    private OrderBuyRepository orderBuyRepository;

    @Test
    @DisplayName("แสดงข้อมูล shipping กรณีจัดส่งแบบธรรมดา")
    void case01() {
        // Arrange
        OrderBuy orderList = new OrderBuy("order-test-id", 11, 22, 33, 44, null);
        List<OrderBuy> listDb = new ArrayList<>(1);
        listDb.add(orderList);
        when(orderBuyRepository.findByOrderId("order-test-id")).thenReturn(Optional.of(listDb));

        // Act
        ShippingResponse result = testRestTemplate.getForObject("/checkout/shipping/order-test-id", ShippingResponse.class);

        // Assert
        assertFalse(result.getEms());
    }

    @Test
    @DisplayName("แสดงข้อมูล shipping กรณีจัดส่งแบบด่วน")
    void case02() {
        // Arrange
        OrderBuy orderList = new OrderBuy("order-test-id", 11, 22, 33, 44, null);
        orderList.setEms(true);
        List<OrderBuy> listDb = new ArrayList<>(1);
        listDb.add(orderList);
        when(orderBuyRepository.findByOrderId("order-test-id")).thenReturn(Optional.of(listDb));

        // Act
        ShippingResponse result = testRestTemplate.getForObject("/checkout/shipping/order-test-id", ShippingResponse.class);

        // Assert
        assertTrue(result.getEms());
    }

    @Test
    @DisplayName("แสดงข้อมูล shipping แล้วได้รับ JSON Object error กรณี Order id not found")
    void case03() {
        // Arrange
        when(orderBuyRepository.findByOrderId("order-test-id")).thenReturn(Optional.empty());

        // Act
        ExceptionModel exception = testRestTemplate.getForObject("/checkout/shipping/order-test-id", ExceptionModel.class);

        // Assert
        String expectedMessage = "Order id: order-test-id not found";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
        assertThat(exception.getStatus(), equalTo(HttpStatus.NOT_FOUND.value()));
    }
}