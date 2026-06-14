package com.mulit.bookstore.orders;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Import(TestcontainersConfiguration.class)
@SpringBootTest(properties = {"spring.testcontainers.enabled=false"})
class OrderServiceApplicationTests extends AbstractIT {

    @Test
    void contextLoads() {}
}
