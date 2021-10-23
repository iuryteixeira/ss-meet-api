package com.ss.meet.meetapi.infra.repository.config;

import javax.sql.DataSource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class DatabaseConfig {
    
    //@Bean
    public DataSource dataSource() {
        String dbUrl = System.getenv("DATASOURCE_URL");
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(dbUrl);
        return new HikariDataSource(config);
    }

}
