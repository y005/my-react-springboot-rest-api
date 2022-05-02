package com.example.myreactspringbootrestapi.repository;

import com.example.myreactspringbootrestapi.WebAppContext;
import com.example.myreactspringbootrestapi.domain.Product;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;

@SpringJUnitConfig(classes = {WebAppContext.class})
class JdbcProductRepositoryTest {
    @Autowired
    private JdbcProductRepository jdbcProductRepository;

    @BeforeEach
    void setUp() {
        jdbcProductRepository.deleteAll();
    }

    @Test
    void createProduct() {
        jdbcProductRepository.createProduct(new Product("zelda", "adventure", 50, 75000, "pic"));

        Product result = jdbcProductRepository.getProducts(0, 5).get(0);

        assertThat(result.getName(), is("zelda"));
        assertThat(result.getGenre().toString(), is("adventure"));
        assertThat(result.getQuantity(), is(50l));
        assertThat(result.getPrice(), is(75000l));
        assertThat(result.getImg(), is("pic"));
    }

    @Test
    void updateProduct() {
        jdbcProductRepository.createProduct(new Product("zelda", "adventure", 50, 75000, "pic"));
        Product result = jdbcProductRepository.getProducts(0, 5).get(0);
        result.setName("link");
        result.setGenre("puzzle");
        result.setQuantity(100l);
        result.setPrice(80000l);
        result.setImg("img");

        jdbcProductRepository.updateProduct(result);
        Product result1 = jdbcProductRepository.getProducts(0, 5).get(0);

        assertThat(result1.getName(), is("link"));
        assertThat(result1.getGenre().toString(), is("puzzle"));
        assertThat(result1.getQuantity(), is(100l));
        assertThat(result1.getPrice(), is(80000l));
        assertThat(result1.getImg(), is("img"));
    }

    @Test
    void deleteProductByName() {
        jdbcProductRepository.createProduct(new Product("zelda", "adventure", 50, 75000, "pic"));

        int result = jdbcProductRepository.getProducts(0, 5).size();
        jdbcProductRepository.deleteProductByName("zelda");
        int result1 = jdbcProductRepository.getProducts(0, 5).size();

        assertThat(result, is(1));
        assertThat(result1, is(0));
    }

    @Test
    void deleteProductById() {
        jdbcProductRepository.createProduct(new Product("zelda", "adventure", 50, 75000, "pic"));

        long id = jdbcProductRepository.getProducts(0, 5).get(0).getId();
        jdbcProductRepository.deleteProductById(id);
        int result1 = jdbcProductRepository.getProducts(0, 5).size();

        assertThat(result1, is(0));
    }

    @Test
    void getProducts() {
        jdbcProductRepository.createProduct(new Product("zelda", "adventure", 50, 75000, "pic"));

        Product result = jdbcProductRepository.getProducts(0, 5).get(0);

        assertThat(result.getName(), is("zelda"));
        assertThat(result.getGenre().toString(), is("adventure"));
        assertThat(result.getQuantity(), is(50l));
        assertThat(result.getPrice(), is(75000l));
        assertThat(result.getImg(), is("pic"));
    }

    @Test
    void getProductById() {
        jdbcProductRepository.createProduct(new Product("zelda", "adventure", 50, 75000, "pic"));

        long id = jdbcProductRepository.getProducts(0, 5).get(0).getId();
        Product result = jdbcProductRepository.getProductById(id).get();

        assertThat(result.getName(), is("zelda"));
        assertThat(result.getGenre().toString(), is("adventure"));
        assertThat(result.getQuantity(), is(50l));
        assertThat(result.getPrice(), is(75000l));
        assertThat(result.getImg(), is("pic"));
    }

    @Test
    void getProductByName() {
        jdbcProductRepository.createProduct(new Product("zelda", "adventure", 50, 75000, "pic"));

        Product result = jdbcProductRepository.getProductByName("zelda").get();

        assertThat(result.getName(), is("zelda"));
        assertThat(result.getGenre().toString(), is("adventure"));
        assertThat(result.getQuantity(), is(50l));
        assertThat(result.getPrice(), is(75000l));
        assertThat(result.getImg(), is("pic"));
    }
}