package com.example.myreactspringbootrestapi.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Getter
@AllArgsConstructor
public class OrderItem {
    @NotNull
    private long id;
    @Positive(message = "Quantity should be positive")
    private long quantity;
    @Positive(message = "Price should be positive")
    private long price;
}
