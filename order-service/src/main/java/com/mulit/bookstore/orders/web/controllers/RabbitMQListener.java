package com.mulit.bookstore.orders.web.controllers;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQListener {

    @RabbitListener(queues = "${orders.new-orders-queue:new-orders}")
    public void handleNewOrder(MyPayload payload) {
        System.out.println("New Order:" + payload.content());
    }

    @RabbitListener(queues = "${orders.delivered-orders-queue:delivered-orders}")
    public void handleDeliveredOrder(MyPayload payload) {
        System.out.println("Delivered Order:" + payload.content());
    }
}
