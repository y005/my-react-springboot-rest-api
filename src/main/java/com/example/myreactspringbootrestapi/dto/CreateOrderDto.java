package com.example.myreactspringbootrestapi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import java.util.List;

@Getter
@AllArgsConstructor
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
    private List<@Valid CreateOrderItemDto> orderItems;
}
