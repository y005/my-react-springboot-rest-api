package com.example.myreactspringbootrestapi.service;

import com.example.myreactspringbootrestapi.domain.OrderItem;

import java.util.List;

public interface OrderService {
    public void createOrder(String email, String address, String postcode, long totalPrice, List<OrderItem> orderItems);
}
