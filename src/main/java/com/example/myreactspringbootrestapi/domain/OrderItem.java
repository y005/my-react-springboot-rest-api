package com.example.myreactspringbootrestapi.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class OrderItem {
    private long id;
    private String genre;
    private long quantity;
    private long price;
}
