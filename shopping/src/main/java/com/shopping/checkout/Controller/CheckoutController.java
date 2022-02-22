package com.shopping.checkout.Controller;

import com.shopping.checkout.db.OrderBuy;
import com.shopping.checkout.db.Payment;
import com.shopping.checkout.model.*;
import com.shopping.checkout.service.CheckoutService;
import com.user.db.Address;
import com.user.db.ScmUser;
import com.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        int countEms = 0;
        for (OrderBuy orderList : orderBuys) {
            if (orderList.isEms()) {
                countEms++;
            }
        }

        // ตรงข้อมูลส่งสินค้า
        // ถ้า isEms = true หน้า front จะแสดงข้อความ ส่งแบบด่วน: ฟรี
        // ถ้า isEms = false หน้า front จะแสดงข้อความ ส่งแบบธรรมดา: ฟรี
        boolean isEms = countEms == orderBuys.size();
        response.setIsEms(isEms);
        response.setDeliveryTime(deliveryTime(isEms));
    }

    private String deliveryTime(boolean isEms) {
        Calendar calendar = Calendar.getInstance();
        if (isEms) {
            calendar.add(Calendar.DAY_OF_YEAR, 3);
        } else {
            calendar.add(Calendar.DAY_OF_YEAR, 7);
        }

        Date deliveryDate = calendar.getTime();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.UK);
        return simpleDateFormat.format(deliveryDate);
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
            shoppingItem.setVat(item.getVat());
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
}
