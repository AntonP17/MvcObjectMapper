package com.example.mvcobjectmapper.controllers;

import com.example.mvcobjectmapper.model.Order;
import com.example.mvcobjectmapper.service.OrderService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedModel;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public String createOrder(@Valid @RequestBody String orderJson) throws IOException {
        Order order = objectMapper.readValue(orderJson, Order.class);
        Order createdOrder = orderService.createOrder(order);
        return objectMapper.writeValueAsString(createdOrder);
    }

    @GetMapping("/{id}")
    public String getOrderById(@PathVariable Long id) throws IOException {
        Order order = orderService.getOrderById(id)
                .orElseThrow(() -> new NoSuchElementException("Order not found"));
        return objectMapper.writeValueAsString(order);
    }
}
