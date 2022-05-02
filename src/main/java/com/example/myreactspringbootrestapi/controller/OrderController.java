package com.example.myreactspringbootrestapi.controller;

import com.example.myreactspringbootrestapi.dto.CreateOrderDto;
import com.example.myreactspringbootrestapi.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/v1/orders")
public class OrderController {
    private OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping(value = "",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String createOrder(@RequestBody CreateOrderDto createOrderDto) {
        try {
            orderService.createOrder(createOrderDto.getEmail(), createOrderDto.getAddress(), createOrderDto.getPostcode(), createOrderDto.getTotalPrice(), createOrderDto.getOrderItems());
        }
        catch (Exception e) {
            return e.getMessage();
        }
        return "successfully create order";
    }
}
