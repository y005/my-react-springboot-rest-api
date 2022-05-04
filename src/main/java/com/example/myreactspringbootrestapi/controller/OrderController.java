package com.example.myreactspringbootrestapi.controller;

import com.example.myreactspringbootrestapi.dto.CreateOrderDto;
import com.example.myreactspringbootrestapi.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController extends ResponseEntityExceptionHandler {
    private OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping(value = "",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public String createOrder(@RequestBody @Valid CreateOrderDto createOrderDto) {
        orderService.createOrder(createOrderDto.getEmail(), createOrderDto.getAddress(), createOrderDto.getPostcode(), createOrderDto.getTotalPrice(), createOrderDto.getOrderItems());
        return "successfully create order";
    }
}

