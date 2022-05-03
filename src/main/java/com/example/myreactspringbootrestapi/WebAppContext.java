package com.example.myreactspringbootrestapi;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;

@Configuration
@ComponentScan
public class WebAppContext {
    @Bean
    public DataSource dataSource(AppProperties appProperties) {
        return DataSourceBuilder.create()
                .driverClassName(appProperties.getDb_driver_class())
                .url(appProperties.getDb_url())
                .username(appProperties.getDb_user())
                .password(appProperties.getDb_pwd())
                .build();
    }

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean
    public SimpleJdbcInsert simpleJdbcInsert(DataSource dataSource) {
        return new SimpleJdbcInsert(dataSource).withTableName("mysql.products").usingGeneratedKeyColumns("id");
    }

    @Bean
    public NamedParameterJdbcTemplate namedParameterJdbcTemplate(JdbcTemplate jdbcTemplate) {
        return new NamedParameterJdbcTemplate(jdbcTemplate);
    }
}
