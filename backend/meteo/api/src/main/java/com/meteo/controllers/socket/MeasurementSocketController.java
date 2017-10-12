package com.meteo.controllers.socket;

import com.meteo.GameState;
import com.meteo.repositories.MeasurementRepositoryResource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Slf4j
@Controller
class MeasurementSocketController {

    @Autowired
    private MeasurementRepositoryResource repository;

    /*@SendTo(Measurements.TOPIC_MEASUREMENT)
//    @SuppressWarnings("unused")
    @MessageMapping(Measurements.MEASUREMENT)
    public void save(Measurement measurement) {
        log.info("Measurement post {s}", measurement);
        repository.save(measurement);
    }*/

    @MessageMapping("/create/{uuid}")
    @SendTo("/topic/board/{uuid}")
    public GameState createGame(@DestinationVariable String uuid) {
        return new GameState(uuid);
    }

}