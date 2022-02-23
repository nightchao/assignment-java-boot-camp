package com.shopping.checkout.service;

import com.exception.OrderNotFoundException;
import com.exception.PaymentNotFoundException;
import com.exception.SummaryNotFoundException;
import com.shopping.checkout.db.OrderBuy;
import com.shopping.checkout.db.Payment;
import com.shopping.checkout.db.Summary;
import com.shopping.checkout.repo.OrderBuyRepository;
import com.shopping.checkout.repo.PaymentRepository;
import com.shopping.checkout.repo.SummaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CheckoutService {

    @Autowired
    private OrderBuyRepository orderBuyRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private SummaryRepository summaryRepository;

    public void setOrderBuyRepository(OrderBuyRepository orderBuyRepository) {
        this.orderBuyRepository = orderBuyRepository;
    }

    public void setPaymentRepository(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public void setSummaryRepository(SummaryRepository summaryRepository) {
        this.summaryRepository = summaryRepository;
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

    public List<Payment> getAllPayment() {
        List<Payment> list = paymentRepository.findAll();
        if (list.isEmpty()) {
            throw new PaymentNotFoundException("Payment method not found");
        }

        return list;
    }

    public void saveSummary(Summary summary) {
        this.summaryRepository.save(summary);
    }

    public Summary getSummary(int invoiceNo) {
        Optional<Summary> summary = summaryRepository.findById(invoiceNo);
        if (summary.isPresent()) {
            return summary.get();
        }

        throw new SummaryNotFoundException(invoiceNo);
    }
}
