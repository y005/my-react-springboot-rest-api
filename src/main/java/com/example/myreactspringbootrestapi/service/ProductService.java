package com.example.myreactspringbootrestapi.service;

import com.example.myreactspringbootrestapi.domain.Genre;
import com.example.myreactspringbootrestapi.domain.Product;
import com.example.myreactspringbootrestapi.exception.NoGameGenreException;

import java.util.List;

public interface ProductService {
    public void createProduct(String name, String genre, long quantity, long price, String img) throws NoGameGenreException;
    public void updateProduct(String name, String genre, long quantity, long price, String img) throws NoGameGenreException;
    public void deleteProductByName(String name);
    public void deleteProductById(long id);
    public List<Product> getProducts(long page, long size);
    public List<Product> getProductByName(String name);
    public List<Product> getProductById(long id);
    public List<Product> getProductsByGenre(long page, long size, String genre);
    public List<Genre> getProductGenres();

}
