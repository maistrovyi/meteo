package com.meteo.controllers.socket;

import com.meteo.models.Measurement;
import com.meteo.repositories.MeasurementRepositoryResource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import static com.meteo.configs.ApplicationConstants.Endpoints.MEASUREMENT;
import static com.meteo.configs.ApplicationConstants.Endpoints.SOCKET_MEASUREMENT;

@Slf4j
@Controller
class MeasurementSocketController {

    @Autowired
    private MeasurementRepositoryResource repository;

    @MessageMapping(SOCKET_MEASUREMENT)
    @SendTo("/topic/measurement")
    public void save(Measurement measurement) {
        log.info("Measurement post {s}", measurement);
         repository.save(measurement);
    }

    @MessageMapping(MEASUREMENT)
    @SendTo("/connectivity/test")
    public void testConnection(String message) {
        log.info("Connectivity checking request.");
    }

}