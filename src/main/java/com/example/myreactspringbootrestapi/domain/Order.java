package com.example.myreactspringbootrestapi.domain;

import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;
import java.util.List;

@Getter
public class Order {
    @NotBlank
    private Email email;
    @NotBlank
    private String address;
    @NotBlank
    private String postcode;
    @Positive(message = "Total price should be positive")
    private long totalPrice;
    @NotNull
    private OrderStatus orderStatus;
    @NotEmpty
    private List<OrderItem> orderItems;
    @NotNull
    private LocalDateTime createdAt;
    @NotNull
    private LocalDateTime updatedAt;

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
