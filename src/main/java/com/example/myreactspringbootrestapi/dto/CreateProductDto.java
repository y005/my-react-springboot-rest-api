package com.example.myreactspringbootrestapi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@Getter
@AllArgsConstructor
public class CreateProductDto {
    @NotBlank
    private String name;
    private String genre;
    @Positive(message = "Quantity should be positive")
    private Long quantity;
    @Positive(message = "Price should be positive")
    private Long price;
    private String img;
}
