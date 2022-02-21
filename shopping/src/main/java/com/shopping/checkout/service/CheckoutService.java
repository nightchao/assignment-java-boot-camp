package com.shopping.checkout.service;

import com.exception.OrderNotFoundException;
import com.shopping.checkout.db.OrderList;
import com.shopping.checkout.repo.OrderListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CheckoutService {

    @Autowired
    private OrderListRepository orderListRepository;

    public void setOrderListRepository(OrderListRepository orderListRepository) {
        this.orderListRepository = orderListRepository;
    }

    @Transactional
    public void saveOrderList(List<OrderList> listAllOrder) {
        this.orderListRepository.saveAll(listAllOrder);
    }

    public List<OrderList> getOrderById(String orderId) {
        Optional<List<OrderList>> list = this.orderListRepository.findByOrderId(orderId);
        if (list.isPresent()) {
            return list.get();
        }

        throw new OrderNotFoundException(orderId);
    }
}
