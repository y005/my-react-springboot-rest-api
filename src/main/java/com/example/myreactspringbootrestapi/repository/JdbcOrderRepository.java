package com.example.myreactspringbootrestapi.repository;

import com.example.myreactspringbootrestapi.domain.Order;
import com.example.myreactspringbootrestapi.domain.OrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Repository
public class JdbcOrderRepository implements OrderRepository {
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private SimpleJdbcInsert simpleJdbcInsert;
    private JdbcProductRepository jdbcProductRepository;

    private Map<String, Object> getOrderParam(Order order) {
        Map<String,Object> orderParam = new HashMap<>();

        orderParam.put("email",order.getEmail());
        orderParam.put("address",order.getAddress());
        orderParam.put("postcode",order.getPostcode());
        orderParam.put("total_price",order.getTotalPrice());
        orderParam.put("order_status",order.getOrderStatus());
        orderParam.put("createdAt",order.getCreatedAt().toString());
        orderParam.put("updatedAt",order.getUpdatedAt().toString());
        return orderParam;
    }

    private Map<String, Object> getOrderItemParam(OrderItem orderItem, long orderId) {
        Map<String,Object> orderItemParam = new HashMap<>();

        orderItemParam.put("order_id",orderId);
        orderItemParam.put("product_id",orderItem.getId());
        orderItemParam.put("genre",orderItem.getGenre());
        orderItemParam.put("price",orderItem.getPrice());
        orderItemParam.put("quantity",orderItem.getQuantity());
        return orderItemParam;
    }

    @Autowired
    public JdbcOrderRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate, JdbcProductRepository jdbcProductRepository, DataSource dataSource) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        this.simpleJdbcInsert = new SimpleJdbcInsert(dataSource).withTableName("mysql.orders").usingGeneratedKeyColumns("id");
        this.jdbcProductRepository = jdbcProductRepository;
    }

    @Override
    @Transactional
    public void createOrder(Order order) {
//        namedParameterJdbcTemplate.update(
//                "insert into mysql.orders(email, address, postcode, total_price, order_status, createdAt, updatedAt) values(:email, :address, :postcode,:total_price, :order_status, :createdAt, :updatedAt)",
//                getOrderParam(order)
//        );
        long orderId = simpleJdbcInsert.executeAndReturnKey(getOrderParam(order)).longValue();
        order.getOrderItems().stream().forEach((e)->{
             jdbcProductRepository.updateInventory(e.getId(), e.getQuantity());
                namedParameterJdbcTemplate.update(
                        "insert into mysql.orders_item(order_id, product_id, genre, quantity, price) values(:order_id, :product_id, :genre, :quantity, :price)",
                        getOrderItemParam(e, orderId)
                );
        });
    }

    @Override
    public long getOrderIdByEmail(String email) {
        return namedParameterJdbcTemplate.queryForObject(
                "select id from mysql.orders where email=:email",
                Collections.singletonMap("email",email),
                Long.class
        );
    }
}
