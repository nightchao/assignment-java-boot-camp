package com.shopping.checkout.controller;

import com.shopping.checkout.db.OrderBuy;
import com.shopping.checkout.db.Payment;
import com.shopping.checkout.db.Summary;
import com.shopping.checkout.model.*;
import com.shopping.checkout.service.CheckoutService;
import com.user.entity.Address;
import com.user.entity.ScmUser;
import com.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping(value = "/checkout")
public class CheckoutController {

    @Autowired
    private CheckoutService checkoutService;

    @Autowired
    private UserService userService;

    @GetMapping("/shipping/{orderId}")
    public ShippingResponse getShipping(@PathVariable String orderId) {
        List<OrderBuy> orderBuys = checkoutService.getOrderById(orderId);

        ShippingResponse response = new ShippingResponse(orderId);
        getDelivery(response, orderBuys);
        getShoppingItem(response, orderBuys);
        getUserAddress(response, orderBuys);
        return response;
    }

    private void getDelivery(ShippingResponse response, List<OrderBuy> orderBuys) {
        boolean isEms = checkIsEms(orderBuys);
        response.setIsEms(isEms);
        response.setDeliveryTime(deliveryTime(isEms));
    }

    private boolean checkIsEms(List<OrderBuy> orderBuys) {
        int countEms = 0;
        for (OrderBuy orderList : orderBuys) {
            if (orderList.isEms()) {
                countEms++;
            }
        }

        // ตรงข้อมูลส่งสินค้า
        // ถ้า isEms = true หน้า front จะแสดงข้อความ ส่งแบบด่วน: ฟรี
        // ถ้า isEms = false หน้า front จะแสดงข้อความ ส่งแบบธรรมดา: ฟรี
        return countEms == orderBuys.size();
    }

    private String deliveryTime(boolean isEms) {
        Calendar calendar = Calendar.getInstance();
        if (isEms) {
            calendar.add(Calendar.DAY_OF_YEAR, 3);
        } else {
            calendar.add(Calendar.DAY_OF_YEAR, 7);
        }

        return getDateTimeStr(calendar.getTime(), "dd/MM/yyyy");
    }

    private void getShoppingItem(ShippingResponse response, List<OrderBuy> orderBuys) {
        List<ListShoppingItem> listShopping = new ArrayList<>(1);
        ListShoppingItem shoppingItem;
        for (OrderBuy item : orderBuys) {
            shoppingItem = new ListShoppingItem();
            shoppingItem.setProductId(item.getProductId());
            shoppingItem.setQuantity(item.getQuantity());
            shoppingItem.setName(item.getName());
            shoppingItem.setPrice(item.getPrice());
            listShopping.add(shoppingItem);
        }

        int total = listShopping.size();
        response.setTotal(total);
        response.setListShopping(listShopping);
    }

    public void getUserAddress(ShippingResponse response, List<OrderBuy> orderBuys) {
        int userId = orderBuys.get(0).getUserId();
        ScmUser user = userService.getUser(userId);
        Address address = userService.getAddress(user.getUserId());

        UserAddress userAddress = new UserAddress();
        userAddress.setFullName(user.getFullName());
        userAddress.setEmail(user.getEmail());
        userAddress.setAddressId(address.getAddressId());
        userAddress.setAddress(address.getAddress());
        userAddress.setDistrict(address.getDistrict());
        userAddress.setProvince(address.getProvince());
        userAddress.setTelephone(address.getTelephone());
        response.setUserAddress(userAddress);
    }

    @GetMapping("/payment/method")
    public PaymentMethodResponse getPaymentMethod() {
        List<Payment> paymentList = checkoutService.getAllPayment();
        List<ListPaymentMethodItem> listPaymentMethod = new ArrayList<>(1);
        ListPaymentMethodItem paymentMethodItem;
        for (Payment payment : paymentList) {
            paymentMethodItem = new ListPaymentMethodItem();
            paymentMethodItem.setPaymentMethodId(payment.getPaymentId());
            paymentMethodItem.setName(payment.getName());
            listPaymentMethod.add(paymentMethodItem);
        }
        return new PaymentMethodResponse(listPaymentMethod);
    }

    @PutMapping("/{orderId}")
    public ConfirmOrderResponse updateOrder(@PathVariable String orderId, @Valid @RequestBody ConfirmOrderRequest input) {
        List<OrderBuy> orderBuys = checkoutService.getOrderById(orderId);
        int userId = orderBuys.get(0).getUserId();
        ScmUser user = userService.getUser(userId);

        Summary summary = new Summary();
        summary.setOrderId(orderId);
        summary.setPayer(user.getFullName());
        Calendar calendar = Calendar.getInstance();
        summary.setTransactionDate(getDateTime(calendar, 0));
        summary.setExpiredDate(getDateTime(calendar, 1));
        summary.setPayee("องค์กร Saladar");
        summary.setDetail("Saladar");
        summary.setAmount(findAmount(orderBuys));
        summary.setPaymentMethodId(input.getPaymentMethodId());
        summary.setIsGetNews(input.getIsGetNews());
        summary.setIsReceiptVat(input.getIsReceiptVat());
        checkoutService.saveSummary(summary);
        return new ConfirmOrderResponse(summary.getInvoiceNo(), "Update Success");
    }

    private Date getDateTime(Calendar calendar, int day) {
        calendar.add(Calendar.DAY_OF_YEAR, day);
        return calendar.getTime();
    }

    private int findAmount(List<OrderBuy> orderBuys) {
        int total = 0;
        for (OrderBuy orderBuy : orderBuys) {
            total = total + orderBuy.getPrice();
        }

        return total;
    }

    @GetMapping("/summary/{invoiceNo}")
    public SummaryResponse getSummaryDetail(@PathVariable Integer invoiceNo) {
        Summary summary = checkoutService.getSummary(invoiceNo);

        SummaryResponse response = new SummaryResponse();
        response.setInvoiceNo(summary.getInvoiceNo());
        response.setPayer(summary.getPayer());
        response.setTransactionDate(getDateTimeStr(summary.getTransactionDate(), "dd/MM/yyyy HH:mm"));
        response.setExpiredDate(getDateTimeStr(summary.getExpiredDate(), "dd/MM/yyyy HH:mm"));
        response.setPayee(summary.getPayee());
        response.setDetail(summary.getDetail());
        response.setAmount(summary.getAmount());
        return response;
    }

    private String getDateTimeStr(Date date, String formatDate) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(formatDate, Locale.UK);
        return simpleDateFormat.format(date);
    }
}
