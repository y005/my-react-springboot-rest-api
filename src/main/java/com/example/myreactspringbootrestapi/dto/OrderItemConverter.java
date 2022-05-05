package com.example.myreactspringbootrestapi.dto;

import com.example.myreactspringbootrestapi.domain.OrderItem;

public class OrderItemConverter {
    public static OrderItem toOrderItem(CreateOrderItemDto createOrderItemDto) {
        return new OrderItem(createOrderItemDto.getId(), createOrderItemDto.getQuantity(), createOrderItemDto.getPrice());
    }
}
