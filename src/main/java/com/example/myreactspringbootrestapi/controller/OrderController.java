package com.example.myreactspringbootrestapi.controller;

import com.example.myreactspringbootrestapi.domain.OrderItem;
import com.example.myreactspringbootrestapi.dto.CreateOrderDto;
import com.example.myreactspringbootrestapi.dto.OrderItemConverter;
import com.example.myreactspringbootrestapi.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {
    private OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping(value = "",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public String createOrder(@RequestBody @Valid CreateOrderDto createOrderDto) {
        List<OrderItem> orderItemList = createOrderDto.getOrderItems().stream().map(OrderItemConverter::toOrderItem).toList();
        orderService.createOrder(createOrderDto.getEmail(), createOrderDto.getAddress(), createOrderDto.getPostcode(), createOrderDto.getTotalPrice(), orderItemList);
        return "successfully create order";
    }
}

