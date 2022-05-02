package com.example.myreactspringbootrestapi.repository;

import com.example.myreactspringbootrestapi.domain.Order;

public interface OrderRepository {
    public void createOrder(Order order);
    public long getOrderIdByEmail(String email);
}
