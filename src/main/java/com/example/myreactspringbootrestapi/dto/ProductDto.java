package com.example.myreactspringbootrestapi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProductDto {
    private long id ;
    private String name;
    private String genre;
    private long quantity;
    private long price;
    private String img;
}
