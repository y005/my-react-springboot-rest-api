package com.example.myreactspringbootrestapi.repository;

import com.example.myreactspringbootrestapi.domain.Genre;
import com.example.myreactspringbootrestapi.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.*;

@Repository
public class JdbcProductRepository implements ProductRepository {
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private SimpleJdbcInsert simpleJdbcInsert;

    private RowMapper<Product> productRowMapper = (resultSet, i)-> {
        long id = resultSet.getLong("id");
        String name = resultSet.getString("name");
        String genre = resultSet.getString("genre");
        long quantity = resultSet.getLong("quantity");
        long price = resultSet.getLong("price");
        String img = resultSet.getString("img");
        LocalDateTime createdAt = resultSet.getTimestamp("createdAt").toLocalDateTime();
        LocalDateTime updatedAt = resultSet.getTimestamp("updatedAt").toLocalDateTime();

        return new Product(id, name, genre, quantity, price, img, createdAt, updatedAt);
    };

    private Map<String, Object> getProductParam(Product product) {
        Map<String,Object> productParam = new HashMap<>();

        productParam.put("name",product.getName());
        productParam.put("genre",product.getGenre());
        productParam.put("price",product.getPrice());
        productParam.put("quantity",product.getQuantity());
        productParam.put("img",product.getImg());
        productParam.put("createdAt",product.getCreatedAt().toString());
        productParam.put("updatedAt",product.getUpdatedAt().toString());
        return productParam;
    }

    @Autowired
    public JdbcProductRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate, DataSource dataSource) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        this.simpleJdbcInsert = new SimpleJdbcInsert(dataSource).withTableName("mysql.products").usingGeneratedKeyColumns("id");
    }

    @Override
    public void createProduct(Product product) {
        namedParameterJdbcTemplate.update(
          "insert into mysql.products(name, genre, price, quantity, img, createdAt, updatedAt) values(:name, :genre, :price, :quantity, :img, :createdAt, :updatedAt)",
                getProductParam(product));
    }

    @Override
    public void updateProduct(Product product) {
        Map<String, Object> productParam = getProductParam(product);
        productParam.put("id", product.getId());
        productParam.put("updatedAt", LocalDateTime.now().toString());
        namedParameterJdbcTemplate.update(
                "update mysql.products set name=:name, genre=:genre, price=:price, quantity=:quantity, img=:img, updatedAt=:updatedAt where id=:id",
                productParam
        );
    }

    @Override
    public void deleteProductByName(String name) {
        namedParameterJdbcTemplate.update(
                "delete from mysql.products where name=:name",
                Collections.singletonMap("name", name)
        );
    }

    @Override
    public void deleteProductById(long id) {
        namedParameterJdbcTemplate.update(
                "delete from mysql.products where id=:id",
                Collections.singletonMap("id", id)
        );
    }

    @Override
    public List<Product> getProducts(long page, long size) {
        Map<String, Object> params = new HashMap<>();
        params.put("page", page);
        params.put("size", size);
        return namedParameterJdbcTemplate.query(
                "select * from mysql.products order by id limit "+ size +" offset "+page*size,
                params,
                productRowMapper
        );
    }

    @Override
    public List<Product> getProductsByGenre(long page, long size, String genre) {
        return namedParameterJdbcTemplate.query(
                "select * from mysql.products where genre=:genre order by id limit "+ size +" offset "+page*size,
                Collections.singletonMap("genre", genre),
                productRowMapper
        );
    }

    @Override
    public Optional<Product> getProductById(long id) {
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        return Optional.ofNullable(namedParameterJdbcTemplate.queryForObject(
                "select * from mysql.products where id=:id",
                params,
                productRowMapper
        ));
    }

    @Override
    public List<Genre> getProductGenres() {
        return namedParameterJdbcTemplate.query(
                "select distinct genre from mysql.products order by genre",
                Collections.emptyMap(),
                new RowMapper<Genre>() {
                    @Override
                    public Genre mapRow(ResultSet rs, int rowNum) throws SQLException {
                        return Genre.toGenre(rs.getString("genre"));
                    }
                }
        );
    }

    @Override
    public Optional<Product> getProductByName(String name) {
        Map<String, Object> params = new HashMap<>();
        params.put("name", name);
        return Optional.ofNullable(namedParameterJdbcTemplate.queryForObject(
                "select * from mysql.products where name=:name",
                params,
                productRowMapper
        ));
    }

    @Override
    public void deleteAll() {
        namedParameterJdbcTemplate.update(
                "delete from mysql.products where id>=0",
                Collections.emptyMap()
        );
    }

    @Override
    public void updateInventory(long id, long orderQuantity) {
        Product product = getProductById(id).get();

        product.setQuantity(product.getQuantity() - orderQuantity);
        updateProduct(product);
    }
}
