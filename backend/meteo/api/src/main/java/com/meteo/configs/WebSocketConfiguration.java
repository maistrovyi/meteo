/*
package com.meteo.configs;

import com.meteo.configs.ApiConstants.Endpoints;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

import javax.annotation.Nonnull;

@SpringBootConfiguration
@EnableWebSocketMessageBroker
public class WebSocketConfiguration extends AbstractWebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.enableSimpleBroker(Endpoints.TOPIC);
        registry.setApplicationDestinationPrefixes(Endpoints.APP);
    }

    @Override
    public void registerStompEndpoints(@Nonnull StompEndpointRegistry registry) {
        registry
                .addEndpoint(Endpoints.WEBSOCKET)
                .setAllowedOrigins("*")
                .withSockJS();
    }

}*/
