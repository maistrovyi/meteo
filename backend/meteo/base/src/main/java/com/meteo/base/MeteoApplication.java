package com.meteo.base;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.rest.RepositoryRestMvcAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {RepositoryRestMvcAutoConfiguration.class, SecurityAutoConfiguration.class}, scanBasePackages = "com.meteo.*")
public class MeteoApplication {

    public static void main(String[] args) {
        SpringApplication.run(MeteoApplication.class, args);
    }

}