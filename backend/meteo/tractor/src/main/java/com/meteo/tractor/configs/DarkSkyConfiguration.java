package com.meteo.tractor.configs;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import tk.plogitech.darksky.api.jackson.DarkSkyJacksonClient;

@SpringBootConfiguration
public class DarkSkyConfiguration {

    @Bean
    public DarkSkyJacksonClient darkSkyJacksonClient() {
        return new DarkSkyJacksonClient();
    }

}