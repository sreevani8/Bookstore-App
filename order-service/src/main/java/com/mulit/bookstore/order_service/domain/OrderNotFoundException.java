package com.mulit.bookstore.order_service.domain;

public class OrderNotFoundException extends RuntimeException{
    public OrderNotFoundException(String message){
        super(message);
    }
    public static OrderNotFoundException forOrderNumber(String orderNumber){
        return new OrderNotFoundException("Order number " + orderNumber + " not found");
    }
}
