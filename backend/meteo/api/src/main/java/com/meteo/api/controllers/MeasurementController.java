package com.meteo.api.controllers;

import com.meteo.api.dto.CountDto;
import com.meteo.warehouse.models.Measurement;
import com.meteo.warehouse.repositories.MeasurementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.util.List;

import static com.meteo.api.configs.ApiConstants.Endpoints.API_MEASUREMENT;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

@RestController
@RequestMapping(value = API_MEASUREMENT, produces = APPLICATION_JSON_UTF8_VALUE)
class MeasurementController {

    @Autowired
    private MeasurementRepository repository;

    @GetMapping(value = "count")
    public ResponseEntity<CountDto> count() {
        return ResponseEntity.ok(CountDto.toDto(repository.count()));
    }

    @GetMapping
    public List<Measurement> findAll() {
        return repository.findAll();
    }

    @GetMapping(value = "today")
    public List<Measurement> findAllToday() {
        return repository.findAllByTimeBetween(ZonedDateTime.now().with(LocalTime.MIN), ZonedDateTime.now().with(LocalTime.MAX));
    }

    @GetMapping(value = "day")
    public List<Measurement> findAllToday(@RequestParam(value = "day") @DateTimeFormat(iso = ISO.DATE) ZonedDateTime date) {
        return repository.findAllByTimeBetween(date.with(LocalTime.MIN), date.with(LocalTime.MAX));
    }

}