package com.shopping.checkout.controller;

import com.exception.ExceptionModel;
import com.shopping.checkout.db.OrderBuy;
import com.shopping.checkout.db.Payment;
import com.shopping.checkout.db.Summary;
import com.shopping.checkout.model.*;
import com.shopping.checkout.repo.OrderBuyRepository;
import com.shopping.checkout.repo.PaymentRepository;
import com.shopping.checkout.repo.SummaryRepository;
import com.user.db.Address;
import com.user.db.ScmUser;
import com.user.repo.AddressRepository;
import com.user.repo.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import java.util.ArrayList;
import java.util.Date;
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

    @MockBean
    private AddressRepository addressRepository;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private PaymentRepository paymentRepository;

    @MockBean
    private SummaryRepository summaryRepository;

    private void initDataOrderBuy(boolean isEms) {
        OrderBuy orderBuy = new OrderBuy("order-test-id", 11, 22, 33, 100);
        orderBuy.setName("test product");
        orderBuy.setEms(isEms);
        List<OrderBuy> listDb = new ArrayList<>(1);
        listDb.add(orderBuy);
        when(orderBuyRepository.findByOrderId("order-test-id")).thenReturn(Optional.of(listDb));
    }

    private void initDataOrderBuyCaseEmpty() {
        when(orderBuyRepository.findByOrderId("order-test-id")).thenReturn(Optional.empty());
    }

    private void initDataUser() {
        ScmUser user = new ScmUser(11, "test fullName");
        when(userRepository.findById(11)).thenReturn(Optional.of(user));
    }

    private void initDataUserCaseEmpty() {
        when(userRepository.findById(11)).thenReturn(Optional.empty());
    }

    private void initDataAddress() {
        Address address = new Address(55, 11);
        address.setAddress("test address");
        when(addressRepository.findAddress(11)).thenReturn(Optional.of(address));
    }

    @Test
    @DisplayName("แสดงข้อมูลทดสอบ shipping กรณีจัดส่งแบบธรรมดา")
    void case01() {
        // Arrange
        initDataOrderBuy(false);
        initDataUser();
        initDataAddress();

        // Act
        ShippingResponse result = testRestTemplate.getForObject("/checkout/shipping/order-test-id", ShippingResponse.class);

        // Assert
        assertFalse(result.getIsEms());
    }

    @Test
    @DisplayName("แสดงข้อมูลทดสอบ shipping กรณีจัดส่งแบบด่วน")
    void case02() {
        // Arrange
        initDataOrderBuy(true);
        initDataUser();
        initDataAddress();

        // Act
        ShippingResponse result = testRestTemplate.getForObject("/checkout/shipping/order-test-id", ShippingResponse.class);

        // Assert
        assertTrue(result.getIsEms());
    }

    @Test
    @DisplayName("แสดงข้อมูลทดสอบ shipping แล้วได้รับ JSON Object error กรณี Order id not found")
    void case03() {
        // Arrange
        initDataOrderBuyCaseEmpty();

        // Act
        ExceptionModel exception = testRestTemplate.getForObject("/checkout/shipping/order-test-id", ExceptionModel.class);

        // Assert
        String expectedMessage = "Order id: order-test-id not found";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
        assertThat(exception.getStatus(), equalTo(HttpStatus.NOT_FOUND.value()));
    }

    @Test
    @DisplayName("แสดงข้อมูลทดสอบสินค้าที่สั่งซื้อ โดยสั่ง orderId = order-test-id แล้วพบสินค้า 1 รายการ")
    void case04() {
        // Arrange
        initDataOrderBuy(false);
        initDataUser();
        initDataAddress();

        // Act
        ShippingResponse result = testRestTemplate.getForObject("/checkout/shipping/order-test-id", ShippingResponse.class);

        // Assert
        assertFalse(result.getListShopping().isEmpty());
        assertEquals(1, result.getListShopping().size());
    }

    @Test
    @DisplayName("ค้นหาข้อมูลทดสอบ ที่อยู่ โดยส่ง userId = 22 แล้วได้ผลลัพธ์โดย address = test address")
    void case05() {
        // Arrange
        initDataOrderBuy(false);
        initDataUser();
        initDataAddress();

        // Act
        ShippingResponse result = testRestTemplate.getForObject("/checkout/shipping/order-test-id", ShippingResponse.class);

        // Assert
        assertEquals("test address", result.getUserAddress().getAddress());
    }

    @Test
    @DisplayName("ค้นหาข้อมูลทดสอบ ที่อยู่ โดยส่ง userId = 22 แล้วได้รับ JSON Object error กรณี User not found")
    void case06() {
        // Arrange
        initDataOrderBuy(false);
        initDataUserCaseEmpty();
        initDataAddress();

        // Act
        ExceptionModel exception = testRestTemplate.getForObject("/checkout/shipping/order-test-id", ExceptionModel.class);

        // Assert
        String expectedMessage = "User id: 11 not found";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
        assertThat(exception.getStatus(), equalTo(HttpStatus.NOT_FOUND.value()));
    }

    @Test
    @DisplayName("ค้นหาข้อมูลทดสอบ ที่อยู่ โดยส่ง userId = 22 แล้วได้รับ JSON Object error กรณี Address not found")
    void case07() {
        // Arrange
        initDataOrderBuy(false);
        initDataUser();
        when(addressRepository.findAddress(11)).thenReturn(Optional.empty());

        // Act
        ExceptionModel exception = testRestTemplate.getForObject("/checkout/shipping/order-test-id", ExceptionModel.class);

        // Assert
        String expectedMessage = "Address not found userId: 11";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
        assertThat(exception.getStatus(), equalTo(HttpStatus.NOT_FOUND.value()));
    }

    @Test
    @DisplayName("แสดงข้อมูลทดสอบวิธีการชำระเงินทั้งหมด")
    void case08() {
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
        PaymentMethodResponse result = testRestTemplate.getForObject("/checkout/payment/method", PaymentMethodResponse.class);

        // Assert
        assertFalse(result.getListPaymentMethod().isEmpty());
        assertEquals(3, result.getListPaymentMethod().size());
        assertEquals("A Method", result.getListPaymentMethod().get(0).getName());
        assertEquals("B Method", result.getListPaymentMethod().get(1).getName());
        assertEquals("C Method", result.getListPaymentMethod().get(2).getName());
    }

    @Test
    @DisplayName("แสดงข้อมูลทดสอบวิธีการชำระเงินทั้งหมด แล้วได้รับ JSON Object error กรณีไม่มีข้อมูล")
    void case09() {
        // Arrange
        when(paymentRepository.findAll()).thenReturn(new ArrayList<>(1));

        // Act
        ExceptionModel exception = testRestTemplate.getForObject("/checkout/payment/method", ExceptionModel.class);

        // Assert
        String expectedMessage = "Payment method not found";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
        assertThat(exception.getStatus(), equalTo(HttpStatus.NOT_FOUND.value()));
    }

    private ConfirmOrderRequest requestConfirmOrder() {
        ConfirmOrderRequest confirmOrderRequest = new ConfirmOrderRequest();
        confirmOrderRequest.setOrderId("order-test-id");
        confirmOrderRequest.setPaymentMethodId(111);
        confirmOrderRequest.setIsReceiptVat(true);
        confirmOrderRequest.setIsGetNews(true);
        return confirmOrderRequest;
    }

    private HttpEntity<ConfirmOrderRequest> getEntityConfirmOrder() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new HttpEntity<>(requestConfirmOrder(), headers);
    }

    @Test
    @DisplayName("นำข้อมูลทดสอบสั่งซื้อสินค้าอัพเดทลงฐานข้อมูล แล้วได้รับ message = Update Success")
    void case10() {
        // Arrange
        initDataOrderBuy(false);
        initDataUser();

        // Act
        ResponseEntity<ConfirmOrderResponse> result = testRestTemplate.exchange("/checkout/order-test-id", HttpMethod.PUT, getEntityConfirmOrder(), ConfirmOrderResponse.class);

        // Assert
        assertEquals(result.getStatusCode(), HttpStatus.OK);
        assertNotNull(result.getBody());
        assertEquals("Update Success", result.getBody().getMessage());
    }

    @Test
    @DisplayName("นำข้อมูลทดสอบสั่งซื้อสินค้าอัพเดทลงฐานข้อมูล แล้วได้รับ JSON Object error กรณี Order not found")
    void case11() {
        // Arrange
        initDataOrderBuyCaseEmpty();

        // Act
        ResponseEntity<ExceptionModel> exception = testRestTemplate.exchange("/checkout/order-test-id", HttpMethod.PUT, getEntityConfirmOrder(), ExceptionModel.class);

        // Assert
        String expectedMessage = "Order id: order-test-id not found";
        assertNotNull(exception.getBody());
        String actualMessage = exception.getBody().getMessage();
        assertEquals(expectedMessage, actualMessage);
        assertThat(exception.getStatusCode(), equalTo(HttpStatus.NOT_FOUND));
    }

    @Test
    @DisplayName("นำข้อมูลทดสอบสั่งซื้อสินค้าอัพเดทลงฐานข้อมูล แล้วได้รับ JSON Object error กรณี User not found")
    void case12() {
        // Arrange
        initDataOrderBuy(false);
        initDataUserCaseEmpty();

        // Act
        ResponseEntity<ExceptionModel> exception = testRestTemplate.exchange("/checkout/order-test-id", HttpMethod.PUT, getEntityConfirmOrder(), ExceptionModel.class);

        // Assert
        String expectedMessage = "User id: 11 not found";
        assertNotNull(exception.getBody());
        String actualMessage = exception.getBody().getMessage();
        assertEquals(expectedMessage, actualMessage);
        assertThat(exception.getStatusCode(), equalTo(HttpStatus.NOT_FOUND));
    }

    @Test
    @DisplayName("แสดงข้อมูลทดสอบ Summary โดยส่ง invoiceNo = 1 แล้วมีผลลัพธ์กลับมา")
    void case13() {
        // Arrange
        Summary summary = new Summary();
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
        when(summaryRepository.findById(1)).thenReturn(Optional.of(summary));

        // Act
        SummaryResponse result = testRestTemplate.getForObject("/checkout/summary/1", SummaryResponse.class);

        // Assert
        assertNotNull(summary);
        assertEquals("test payer", result.getPayer());
        assertEquals(100, result.getAmount());
    }

    @Test
    @DisplayName("แสดงข้อมูลทดสอบ Summary โดยส่ง invoiceNo = 1 แล้วได้รับ JSON Object error กรณี Summary not found")
    void case14() {
        // Arrange
        when(summaryRepository.findById(1)).thenReturn(Optional.empty());

        // Act
        ExceptionModel exception = testRestTemplate.getForObject("/checkout/summary/1", ExceptionModel.class);

        // Assert
        String expectedMessage = "Summary: 1 not found";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
        assertThat(exception.getStatus(), equalTo(HttpStatus.NOT_FOUND.value()));
    }
}