package com.mulit.bookstore.webapp.web.controllers;

import com.mulit.bookstore.webapp.clients.orders.CreateOrderRequest;
import com.mulit.bookstore.webapp.clients.orders.OrderConfirmationDTO;
import com.mulit.bookstore.webapp.clients.orders.OrderDTO;
import com.mulit.bookstore.webapp.clients.orders.OrderServiceClient;
import com.mulit.bookstore.webapp.clients.orders.OrderSummary;
import com.mulit.bookstore.webapp.services.SecurityHelper;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Map;
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
    private final SecurityHelper securityHelper;

    OrderController(OrderServiceClient orderServiceClient, SecurityHelper securityHelper) {
        this.orderServiceClient = orderServiceClient;
        this.securityHelper = securityHelper;
    }

    @GetMapping("/cart")
    String cart() {
        return "cart";
    }

    @PostMapping("/api/orders")
    @ResponseBody
    OrderConfirmationDTO createOrder(@Valid @RequestBody CreateOrderRequest orderRequest) {

        String accessToken = securityHelper.getAccessToken();

        Map<String, ?> headers = Map.of("Authorization", "Bearer " + accessToken);

        return orderServiceClient.createOrder(headers, orderRequest);
    }

    /* @PostMapping("/api/orders")
    @ResponseBody
    OrderConfirmationDTO createOrder(
            @Valid @RequestBody CreateOrderRequest orderRequest) {

        String accessToken = securityHelper.getAccessToken();

        Map<String, ?> headers =
                Map.of("Authorization", "Bearer " + accessToken);

        return orderServiceClient.createOrder(headers, orderRequest);
    }
        }*/

    @GetMapping("/orders/{orderNumber}")
    String showOrderDetails(@PathVariable String orderNumber, Model model) {
        model.addAttribute("orderNumber", orderNumber);
        return "order_details";
    }

    /*@GetMapping("/api/orders/{orderNumber}")
    @ResponseBody
    OrderDTO getOrder(@PathVariable String orderNumber) {
        String accessToken = securityHelper.getAccessToken();
        Map<String, ?> headers = Map.of("Authorization", "Bearer" + accessToken);
        return orderServiceClient.getOrder(headers, orderNumber);
    }*/
    @GetMapping("/api/orders/{orderNumber}")
    @ResponseBody
    OrderDTO getOrder(@PathVariable String orderNumber) {

        String accessToken = securityHelper.getAccessToken();

        Map<String, ?> headers = Map.of("Authorization", "Bearer " + accessToken);

        return orderServiceClient.getOrder(headers, orderNumber);
    }

    @GetMapping("/orders")
    String showOrders() {
        return "orders";
    }

    @GetMapping("/api/orders")
    @ResponseBody
    List<OrderSummary> getOrders() {

        String accessToken = securityHelper.getAccessToken();

        Map<String, ?> headers = Map.of("Authorization", "Bearer " + accessToken);

        return orderServiceClient.getOrders(headers);
    }
}
