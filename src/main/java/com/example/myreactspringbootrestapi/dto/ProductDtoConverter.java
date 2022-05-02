package com.example.myreactspringbootrestapi.dto;

import com.example.myreactspringbootrestapi.domain.Product;

public class ProductDtoConverter {
    public static ProductDto toProductDto(Product product) {
        return new ProductDto(product.getId(), product.getName(), product.getGenre(), product.getQuantity(), product.getPrice(), product.getImg());
    }
}
