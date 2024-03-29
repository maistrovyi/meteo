package com.meteo.warehouse.configs;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Profile(value = "dev")
@SpringBootConfiguration
@EnableTransactionManagement
@EntityScan(basePackages = "com.meteo.warehouse.models")
@EnableJpaRepositories(basePackages = "com.meteo.warehouse.repositories")
public class JpaConfiguration {

    @Value(value = "${spring.datasource.hikari.jdbc-url}")
    private String url;

    @Value(value = "${spring.datasource.hikari.username}")
    private String username;

    @Value(value = "${spring.datasource.hikari.password}")
    private String password;

    @Bean
    public DataSource dataSource() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(this.url);
        config.setUsername(this.username);
        config.setPassword(this.password);
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        return new HikariDataSource(config);
    }

}