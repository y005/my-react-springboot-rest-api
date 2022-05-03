package com.example.myreactspringbootrestapi.dto;

import com.example.myreactspringbootrestapi.domain.OrderItem;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class CreateOrderDto {
    private String email;
    private String address;
    private String postcode;
    private Long totalPrice;
    private List<OrderItem> orderItems;
}
