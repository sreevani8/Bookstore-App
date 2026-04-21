package com.mulit.bookstore.orders.web.controllers;

import com.mulit.bookstore.orders.AbstractIT;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.jdbc.Sql;

@Sql("/test-orders.sql")
class OrderControllerTests extends AbstractIT {

    @Nested
    class CreateOrderTestsStub {
       @Test
        void createOrder() {
           System.out.println("Test createOrder");

       }
    }

}
