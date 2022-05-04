package com.example.myreactspringbootrestapi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
public class UpdateProductDto {
    @NotNull
    private Long id;
    private String name;
    private String genre;
    private Long quantity;
    private Long price;
    private String img;
}
