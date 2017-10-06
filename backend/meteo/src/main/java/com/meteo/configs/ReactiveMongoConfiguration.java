package com.meteo.configs;

import com.mongodb.ConnectionString;
import com.mongodb.ServerAddress;
import com.mongodb.async.client.MongoClientSettings;
import com.mongodb.connection.ClusterSettings;
import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

import java.util.Collections;

@SpringBootConfiguration
@EnableReactiveMongoRepositories(basePackages = "com.meteo.repositories")
public class ReactiveMongoConfiguration extends AbstractReactiveMongoConfiguration {

    @Value(value = "${spring.data.mongodb.database}")
    private String database;

    @Value(value = "${spring.data.mongodb.host}")
    private String host;

    @Value(value = "${spring.data.mongodb.port}")
    private int port;

    @Override
    public MongoClient reactiveMongoClient() {
        return MongoClients.create(MongoClientSettings.builder()
                .clusterSettings(ClusterSettings.builder()
                        .hosts(Collections.singletonList(new ServerAddress(host)))
                        .applyConnectionString(new ConnectionString(String.format("mongodb://%s:%d", host, port)))
                        .build())
                .build());
    }

    @Override
    protected String getDatabaseName() {
        return this.database;
    }

    @Bean
    public ReactiveMongoTemplate reactiveMongoTemplate() {
        return new ReactiveMongoTemplate(reactiveMongoClient(), getDatabaseName());
    }

}