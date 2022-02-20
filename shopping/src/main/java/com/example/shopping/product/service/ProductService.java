package com.example.shopping.product.service;

import com.example.shopping.product.db.Basket;
import com.example.shopping.product.db.Product;
import com.example.shopping.product.repo.BasketRepository;
import com.example.shopping.product.repo.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private BasketRepository basketRepository;

    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void setBasketRepository(BasketRepository basketRepository) {
        this.basketRepository = basketRepository;
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
}
