package com.meteo.controllers.socket;

import com.meteo.models.Measurement;
import com.meteo.repositories.MeasurementRepositoryResource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.meteo.configs.ApiConstants.Endpoints;

@Slf4j
@Controller
class MeasurementSocketController {

    @Autowired
    private MeasurementRepositoryResource repository;

    @MessageMapping(Endpoints.SOCKET_MEASUREMENT)
    @SendTo("/topic/measurement")
    public void save(Measurement measurement) {
        log.info("Measurement post {s}", measurement);
         repository.save(measurement);
    }

    @MessageMapping(Endpoints.MEASUREMENT)
    @SendTo("/connectivity/test")
    public void testConnection(String message) {
        log.info("Connectivity checking request.");
    }

}