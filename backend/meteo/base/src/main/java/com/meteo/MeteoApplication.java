package com.meteo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackageClasses = {ApiModule.class, WarehouseModule.class})
@SpringBootApplication
public class MeteoApplication {

    public static void main(String[] args) {
        SpringApplication.run(MeteoApplication.class, args);
    }

}