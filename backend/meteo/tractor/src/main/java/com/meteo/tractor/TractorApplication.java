package com.meteo.tractor;

import com.meteo.tractor.service.WeatherFetchService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@RequiredArgsConstructor
public class TractorApplication {

	private final WeatherFetchService weatherService;

	public static void main(String[] args) {
		SpringApplication.run(TractorApplication.class, args);
	}

	@Bean
	CommandLineRunner runner() {
		return args -> weatherService.fetch();
	}

}