package com.mulit.bookstore.orders.domain;

public class OrderNotFoundException extends RuntimeException {

    public OrderNotFoundException(String message) {
        super(message);
    }

    public static OrderNotFoundException forOrdernumber(String ordernumber) {
        return new OrderNotFoundException("Order with Number" + ordernumber + " not found");
    }
}
