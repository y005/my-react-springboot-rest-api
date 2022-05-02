package com.example.myreactspringbootrestapi;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Getter
@Setter
@Configuration
@EnableConfigurationProperties(value = {AppProperties.class})
@ConfigurationProperties(prefix = "dev")
@PropertySource("application.properties")
public class AppProperties {
    private String db_driver_class = "com.mysql.cj.jdbc.Driver";
    private String db_url = "jdbc:mysql://localhost:3306";
    private String db_user = "root";
    private String db_pwd = "samho101";
}
