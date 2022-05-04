package com.example.myreactspringbootrestapi.domain;


import com.example.myreactspringbootrestapi.exception.NoOrderStatusException;

import java.util.Arrays;
import java.util.List;

public enum OrderStatus {
    ACCEPTED("accepted"),
    PAYMENT_CONFIRMED("payment confirmed"),
    READY_FOR_DELIVERY("ready for delivery"),
    SHIPPED("shipped"),
    SETTLED("settled"),
    CANCELLED("cancelled"),;

    private final String orderStatus;

    OrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public static OrderStatus toOrderStatus(String orderStatus) {
        return Arrays.stream(values()).filter(e->e.toString().equals(orderStatus)).findFirst().orElseThrow(
                ()-> {
                    List<String> validOrderStatus = Arrays.stream(values()).map(OrderStatus::toString).toList();
                    return new NoOrderStatusException("Order status not existed. Valid order status is "+ validOrderStatus);
                });
    }

    @Override
    public String toString() {
        return orderStatus;
    }
}
