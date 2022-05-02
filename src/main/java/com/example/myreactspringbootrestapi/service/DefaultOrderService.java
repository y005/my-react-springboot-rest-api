package com.example.myreactspringbootrestapi.service;

import com.example.myreactspringbootrestapi.domain.Order;
import com.example.myreactspringbootrestapi.domain.OrderItem;
import com.example.myreactspringbootrestapi.repository.JdbcOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultOrderService implements OrderService {
    private JdbcOrderRepository jdbcOrderRepository;

    @Autowired
    public DefaultOrderService(JdbcOrderRepository jdbcOrderRepository) {
        this.jdbcOrderRepository = jdbcOrderRepository;
    }

    @Override
    public void createOrder(String email, String address, String postcode, long totalPrice, List<OrderItem> orderItems) {
        jdbcOrderRepository.createOrder(new Order(email, address, postcode, totalPrice, "accepted", orderItems));
    }
}
