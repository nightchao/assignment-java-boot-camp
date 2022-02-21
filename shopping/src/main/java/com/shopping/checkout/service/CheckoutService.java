package com.shopping.checkout.service;

import com.exception.OrderNotFoundException;
import com.shopping.checkout.db.OrderBuy;
import com.shopping.checkout.repo.OrderBuyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CheckoutService {

    @Autowired
    private OrderBuyRepository orderBuyRepository;

    public void setOrderBuyRepository(OrderBuyRepository orderBuyRepository) {
        this.orderBuyRepository = orderBuyRepository;
    }

    @Transactional
    public void saveOrderBuy(List<OrderBuy> listAllOrder) {
        this.orderBuyRepository.saveAll(listAllOrder);
    }

    public List<OrderBuy> getOrderById(String orderId) {
        Optional<List<OrderBuy>> list = this.orderBuyRepository.findByOrderId(orderId);
        if (!list.isPresent()) {
            throw new OrderNotFoundException(orderId);
        }

        List<OrderBuy> listOrderBuy = list.get();
        if (listOrderBuy.isEmpty()) {
            throw new OrderNotFoundException(orderId);
        }
        return listOrderBuy;
    }
}
