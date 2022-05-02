package com.example.myreactspringbootrestapi.domain;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class Product {
    private long id ;
    private String name;
    private Genre genre;
    private long quantity;
    private long price;
    private String img;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Product(String name, String genre, long quantity, long price, String img) {
        this.name = name;
        this.genre = Genre.toGenre(genre);
        this.quantity = quantity;
        this.price = price;
        this.img = img;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public Product(long id, String name, String genre, long quantity, long price, String img, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.name = name;
        this.genre = Genre.toGenre(genre);
        this.quantity = quantity;
        this.price = price;
        this.img = img;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public void setName(String name) {
        this.name = name;
        setUpdatedAt();
    }

    public void setGenre(String genre) {
        this.genre = Genre.toGenre(genre);
        setUpdatedAt();
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
        setUpdatedAt();
    }

    public void setPrice(long price) {
        this.price = price;
        setUpdatedAt();
    }

    public void setImg(String img) {
        this.img = img;
        setUpdatedAt();
    }

    private void setUpdatedAt() {
        this.updatedAt = LocalDateTime.now();
    }

    public String getGenre() {
        return genre.toString();
    }
}
