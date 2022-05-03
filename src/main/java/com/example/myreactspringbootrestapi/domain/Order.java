package com.example.myreactspringbootrestapi.domain;

import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class Order {
    private Email email;
    private String address;
    private String postcode;
    private long totalPrice;
    private OrderStatus orderStatus;
    private List<OrderItem> orderItems;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    //유효성 검사 처리
    public Order(String email, String address, String postcode, long totalPrice, String orderStatus,List<OrderItem> orderItems) {
        this.email = new Email(email);
        this.address = address;
        this.postcode = postcode;
        this.totalPrice = totalPrice;
        this.orderStatus = OrderStatus.toOrderStatus(orderStatus);
        this.orderItems = orderItems;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public String getOrderStatus() {
        return orderStatus.toString();
    }

    public String getEmail() {
        return email.toString();
    }
}
