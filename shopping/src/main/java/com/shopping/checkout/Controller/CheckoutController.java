package com.shopping.checkout.Controller;

import com.shopping.checkout.db.OrderBuy;
import com.shopping.checkout.model.ShippingResponse;
import com.shopping.checkout.service.CheckoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping(value = "/checkout")
public class CheckoutController {

    @Autowired
    private CheckoutService checkoutService;

    @GetMapping("/shipping/{orderId}")
    public ShippingResponse getShipping(@PathVariable String orderId) {
        List<OrderBuy> orderLists = checkoutService.getOrderById(orderId);

        int countEms = 0;
        for (OrderBuy orderList : orderLists) {
            if (orderList.isEms()) {
                System.out.println("Check: " + orderList.isEms());
                countEms++;
            }
        }

        // ตรงข้อมูลส่งสินค้า
        // ถ้า isEms = true หน้า front จะแสดงข้อความ ส่งแบบด่วน: ฟรี
        // ถ้า isEms = false หน้า front จะแสดงข้อความ ส่งแบบธรรมดา: ฟรี
        boolean isEms = countEms == orderLists.size();
        return new ShippingResponse(isEms, deliveryTime(isEms));
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
}
