package com.example.myreactspringbootrestapi.dto;

import com.example.myreactspringbootrestapi.domain.Genre;
import lombok.Getter;

@Getter
public class ProductDto {
    private long id ;
    private String name;
    private String genre;
    private long quantity;
    private long price;
    private String img;

    public ProductDto(long id, String name, String genre, long quantity, long price, String img) {
        this.id = id;
        this.name = name;
        this.genre = genre;
        this.quantity = quantity;
        this.price = price;
        this.img = img;
    }
}
