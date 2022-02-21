package com.shopping.product.service;

import com.shopping.product.db.Basket;
import com.shopping.product.db.OrderList;
import com.shopping.product.db.Product;
import com.shopping.product.repo.BasketRepository;
import com.shopping.product.repo.OrderListRepository;
import com.shopping.product.repo.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private BasketRepository basketRepository;

    @Autowired
    private OrderListRepository orderListRepository;

    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void setBasketRepository(BasketRepository basketRepository) {
        this.basketRepository = basketRepository;
    }

    public void setOrderListRepository(OrderListRepository orderListRepository) {
        this.orderListRepository = orderListRepository;
    }

    public List<Product> getListProduct(String search) {
        Optional<List<Product>> list = productRepository.findByNameContainingIgnoreCase(search);
        return list.orElse(new ArrayList<>(1));
    }

    public Product getProduct(int productId) {
        Optional<Product> product = productRepository.findById(productId);
        return product.orElse(null);
    }

    public void saveBasket(Basket basket) {
        this.basketRepository.save(basket);
    }

    public List<Basket> getProductInBasket(int userId) {
        Optional<List<Basket>> list = basketRepository.findByUserId(userId);
        return list.orElse(new ArrayList<>(1));
    }

    @Transactional
    public void saveOrderList(List<OrderList> listAllOrder) {
        this.orderListRepository.saveAll(listAllOrder);
    }
}
