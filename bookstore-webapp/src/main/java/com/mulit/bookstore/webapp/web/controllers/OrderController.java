package com.mulit.bookstore.webapp.web.controllers;

import com.mulit.bookstore.webapp.clients.orders.CreateOrderRequest;
import com.mulit.bookstore.webapp.clients.orders.OrderConfirmationDTO;
import com.mulit.bookstore.webapp.clients.orders.OrderDTO;
import com.mulit.bookstore.webapp.clients.orders.OrderServiceClient;
import com.mulit.bookstore.webapp.clients.orders.OrderSummary;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
class OrderController {

    private final OrderServiceClient orderServiceClient;


    OrderController(OrderServiceClient orderServiceClient) {
        this.orderServiceClient = orderServiceClient;

    }

    @GetMapping("/cart")
    String cart() {
        return "cart";
    }

    @PostMapping("/api/orders")
    OrderConfirmationDTO createOrder(@RequestBody CreateOrderRequest orderRequest) {

        return orderServiceClient.createOrder(orderRequest);
    }

    @GetMapping("/orders/{orderNumber}")
    String showOrderDetails(@PathVariable String orderNumber, Model model) {
        model.addAttribute("orderNumber", orderNumber);
        return "order_details";
    }

    @GetMapping("/api/orders/{orderNumber}")
    @ResponseBody
    OrderDTO getOrder(@PathVariable String orderNumber) {
        return orderServiceClient.getOrder(orderNumber);
    }

    @GetMapping("/orders")
    String showOrders() {
        return "orders";
    }

    @GetMapping("/api/orders")
    @ResponseBody
    List<OrderSummary> getOrders() {
        return orderServiceClient.getOrders();
    }

}