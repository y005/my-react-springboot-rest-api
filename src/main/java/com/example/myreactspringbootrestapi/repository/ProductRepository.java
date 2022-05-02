package com.example.myreactspringbootrestapi.repository;

import com.example.myreactspringbootrestapi.domain.Genre;
import com.example.myreactspringbootrestapi.domain.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {
    public void createProduct(Product product);
    public void updateProduct(Product product);
    public void updateInventory(long id, long orderQuantity);
    public void deleteProductByName(String name);
    public void deleteProductById(long id);
    public List<Product> getProducts(long page, long size);
    public List<Product> getProductsByGenre(long page, long size, String genre);
    public Optional<Product> getProductById(long id);
    public List<Genre> getProductGenres();
    public Optional<Product> getProductByName(String name);
    public void deleteAll();
}
