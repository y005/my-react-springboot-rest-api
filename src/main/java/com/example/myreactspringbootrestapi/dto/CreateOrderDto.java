package com.example.myreactspringbootrestapi.dto;

import com.example.myreactspringbootrestapi.domain.OrderItem;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import java.util.List;

@Getter
public class CreateOrderDto {
    @NotBlank
    private String email;
    @NotBlank
    private String address;
    @NotBlank
    private String postcode;
    @Positive(message = "Total price should be positive")
    private Long totalPrice;
    @NotEmpty
    private List<OrderItem> orderItems;

    public CreateOrderDto(String email, String address, String postcode, Long totalPrice, List<OrderItem> orderItems) {
        this.email = email;
        this.address = address;
        this.postcode = postcode;
        this.totalPrice = totalPrice;
        this.orderItems = orderItems;
    }
}
