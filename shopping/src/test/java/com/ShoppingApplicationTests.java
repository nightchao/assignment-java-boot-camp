package com;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ShoppingApplicationTests {

    @Test
    void contextLoads() {
        ShoppingApplication.main(new String[]{});
    }
}
