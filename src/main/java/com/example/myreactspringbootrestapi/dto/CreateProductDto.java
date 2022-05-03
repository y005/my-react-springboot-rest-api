package com.example.myreactspringbootrestapi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateProductDto {
    private String name;
    private String genre;
    private Long quantity;
    private Long price;
    private String img;
}
