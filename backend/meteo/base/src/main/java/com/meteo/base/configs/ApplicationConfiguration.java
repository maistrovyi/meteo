package com.meteo.base.configs;

import org.springframework.boot.SpringBootConfiguration;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

@SpringBootConfiguration
public class ApplicationConfiguration {

    @PostConstruct
    void init() {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
    }

}