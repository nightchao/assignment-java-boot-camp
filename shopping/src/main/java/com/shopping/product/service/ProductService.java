package com.shopping.product.service;

import com.exception.BasketItemNotFoundException;
import com.exception.ProductNotFoundException;
import com.exception.SearchNotFoundException;
import com.shopping.product.db.Basket;
import com.shopping.product.db.Product;
import com.shopping.product.repo.BasketRepository;
import com.shopping.product.repo.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        if (list.isPresent()) {
            return list.get();
        }

        throw new SearchNotFoundException(search);
    }

    public Product getProduct(int productId) {
        Optional<Product> product = productRepository.findById(productId);
        if (product.isPresent()) {
            return product.get();
        }

        throw new ProductNotFoundException(productId);
    }

    public void saveBasket(Basket basket) {
        this.basketRepository.save(basket);
    }

    public List<Basket> getProductInBasket(int userId) {
        Optional<List<Basket>> list = basketRepository.findByUserId(userId);
        if (!list.isPresent()) {
            throw new BasketItemNotFoundException(userId);
        }

        List<Basket> listBasketItem = list.get();
        if (listBasketItem.isEmpty()) {
            throw new BasketItemNotFoundException(userId);
        }
        return listBasketItem;
    }

    public int priceFinal(int price, int discount, Integer vat) {
        int finalPrice = price * (100 - discount) / 100;

        if (vat != null) {
            finalPrice = finalPrice * (vat / 100);
        }
        return finalPrice;
    }
}
