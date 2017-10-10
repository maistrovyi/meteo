package com.meteo.configs;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

import static com.meteo.configs.ApplicationConstants.Endpoints.MEASUREMENT;
import static com.meteo.configs.ApplicationConstants.Endpoints.SOCKET;
import static com.meteo.configs.ApplicationConstants.Endpoints.TOPIC;

@SpringBootConfiguration
@EnableWebSocketMessageBroker
public class WebSocketConfiguration extends AbstractWebSocketMessageBrokerConfigurer {

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint(MEASUREMENT);
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.enableSimpleBroker(TOPIC);
        registry.setApplicationDestinationPrefixes(SOCKET);
    }

}