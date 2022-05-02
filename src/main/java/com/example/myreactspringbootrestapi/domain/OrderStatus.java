package com.example.myreactspringbootrestapi.domain;


import java.util.Arrays;

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
        return Arrays.stream(values()).filter(e->e.toString().equals(orderStatus)).findFirst().get();
    }

    @Override
    public String toString() {
        return orderStatus;
    }
}
