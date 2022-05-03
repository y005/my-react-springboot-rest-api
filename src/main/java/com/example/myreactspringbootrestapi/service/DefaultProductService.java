package com.example.myreactspringbootrestapi.service;

import com.example.myreactspringbootrestapi.domain.Genre;
import com.example.myreactspringbootrestapi.domain.Product;
import com.example.myreactspringbootrestapi.repository.JdbcProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultProductService implements ProductService{
    private JdbcProductRepository jdbcProductRepository;

    @Autowired
    public DefaultProductService(JdbcProductRepository jdbcProductRepository) {
        this.jdbcProductRepository = jdbcProductRepository;
    }

    @Override
    public void createProduct(String name, String genre, long quantity, long price, String img) {
        jdbcProductRepository.createProduct(Product.of(name, genre, quantity, price, img));
    }

    @Override
    public void updateProduct(long id,String name, String genre, long quantity, long price, String img) {
        jdbcProductRepository.updateProduct(Product.of(id, name, genre, quantity, price, img));
    }

    @Override
    public void deleteProductByName(String name) {
        jdbcProductRepository.deleteProductByName(name);
    }

    @Override
    public void deleteProductById(long id) {
        jdbcProductRepository.deleteProductById(id);
    }

    @Override
    public List<Product> getProducts(long page, long size) {
        return jdbcProductRepository.getProducts(page, size);
    }

    @Override
    public List<Product> getProductByName(String name) {
        return jdbcProductRepository.getProductByName(name).stream().toList();
    }

    @Override
    public List<Product> getProductById(long id) {
        return jdbcProductRepository.getProductById(id).stream().toList();
    }

    @Override
    public List<Product> getProductsByGenre(long page, long size, String genre) {
        return jdbcProductRepository.getProductsByGenre(page, size, genre);
    }

    @Override
    public List<Genre> getProductGenres() {
        return jdbcProductRepository.getProductGenres();
    }
}
