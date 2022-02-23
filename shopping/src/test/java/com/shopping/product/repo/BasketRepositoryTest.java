package com.shopping.product.repo;

import com.shopping.product.db.Basket;
import com.shopping.product.db.BasketId;
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
        BasketId id = new BasketId(11, 22);
        Basket basket = new Basket();
        basket.setUserId(id.getUserId());
        basket.setProductId(id.getProductId());
        basket.setQuantity(1);
        basket.setSize(42);
        basket.setImage("https://image/1.jpg");
        basket.setEms(false);
        basketRepository.save(basket);

        // Act
        Optional<List<Basket>> result = basketRepository.findByUserId(11);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(1, result.get().size());
        assertFalse(result.get().get(0).isEms());
    }
}