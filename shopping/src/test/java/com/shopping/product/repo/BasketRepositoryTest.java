package com.shopping.product.repo;

import com.shopping.product.db.Basket;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class BasketRepositoryTest {

    @Autowired
    private BasketRepository basketRepository;

    @Test
    void findByUserId() {
        // Arrange
        Basket basket = new Basket(11, 22);
        basket.setQuantity(1);
        basket.setSize(42);
        basket.setImage("https://image/1.jpg");
        basketRepository.save(basket);

        // Act
        Optional<List<Basket>> result = basketRepository.findByUserId(11);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(1, result.get().size());
    }
}