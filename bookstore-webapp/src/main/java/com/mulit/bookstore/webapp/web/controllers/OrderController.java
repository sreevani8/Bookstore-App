package com.mulit.bookstore.webapp.web.controllers;

import com.mulit.bookstore.webapp.clients.orders.CreateOrderRequest;
import com.mulit.bookstore.webapp.clients.orders.OrderConfirmationDTO;
import com.mulit.bookstore.webapp.clients.orders.OrderDTO;
import com.mulit.bookstore.webapp.clients.orders.OrderServiceClient;
import com.mulit.bookstore.webapp.clients.orders.OrderSummary;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    /*
    @PostMapping("/api/orders")
    OrderConfirmationDTO createOrder(@RequestBody CreateOrderRequest orderRequest) {

        return orderServiceClient.createOrder(orderRequest);
    }*/
    @PostMapping("/api/orders")
    @ResponseBody
    public ResponseEntity<?> createOrder(@RequestBody CreateOrderRequest orderRequest) {
        try {
            System.out.println("Order Request = " + orderRequest);

            OrderConfirmationDTO response = orderServiceClient.createOrder(orderRequest);

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace(); // IMPORTANT
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.toString());
        }
    }

    @GetMapping("/orders/{orderNumber}")
    String showOrderDetails(@PathVariable String orderNumber, Model model) {
        model.addAttribute("orderNumber", orderNumber);
        return "order_details";
    }

    /* @GetMapping("/api/orders/{orderNumber}")
    @ResponseBody
    OrderDTO getOrder(@PathVariable String orderNumber) {
        return orderServiceClient.getOrder(orderNumber);
    }*/

    @GetMapping("/api/orders/{orderNumber}")
    @ResponseBody
    OrderDTO getOrder(@PathVariable String orderNumber) {

        System.out.println("Fetching Order : " + orderNumber);

        OrderDTO order = orderServiceClient.getOrder(orderNumber);

        System.out.println("Order Response : " + order);

        return order;
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
