package com.meteo.controllers.socket;

import com.meteo.models.Measurement;
import com.meteo.repositories.MeasurementRepositoryResource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import static com.meteo.configs.ApiConstants.Endpoints.*;

@Slf4j
@Controller
class MeasurementSocketController {

    @Autowired
    private MeasurementRepositoryResource repository;

    private final String TEST_RESPONSE = "{ Success }";

    @SendTo(Measurements.TOPIC_MEASUREMENT)
    @SuppressWarnings("unused")
    @MessageMapping(Measurements.MEASUREMENT)
    public void save(Measurement measurement) {
        log.info("Measurement post {s}", measurement);
        repository.save(measurement);
    }

    @SendTo(Health.TOPIC_HEALTH)
    @SuppressWarnings("unused")
    @MessageMapping(Health.HEALTH)
    public String testConnection() {
        log.info("Connectivity checking request, preparing response...");
        return TEST_RESPONSE;
    }

}