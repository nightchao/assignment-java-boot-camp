package com.shopping.checkout.service;

import com.shopping.checkout.db.OrderList;
import com.shopping.checkout.repo.OrderListRepository;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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

    public List<OrderList> getOrderById(String id) {
        Optional<List<OrderList>> list = this.orderListRepository.findByOrderId(id);
        return list.orElse(new ArrayList<>(1));
    }
}
