package com.meteo.listeners;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

@Slf4j
public class DefaultMessageListener implements MessageListener {

    @Override
    public void onMessage(Message message) {
        log.info("Process message: {s}", message);
    }
}