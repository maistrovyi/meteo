/*
package com.meteo.tests.integration;

import com.meteo.configs.ApiConstants.Endpoints;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.lang.Nullable;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.stomp.StompFrameHandler;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;
import org.springframework.web.socket.sockjs.client.SockJsClient;
import org.springframework.web.socket.sockjs.client.WebSocketTransport;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.concurrent.*;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.TestInstance.Lifecycle;

@SpringBootTest
@Tag(value = "integration-fast")
@TestInstance(Lifecycle.PER_CLASS)
@RunWith(value = JUnitPlatform.class)
@ExtendWith(value = SpringExtension.class)
class MeasurementSocketControllerTest {

    */
/*//*
websocket/app/measurement
      /websocket/topic/measurement
      /websocket/app/health
      /websocket/topic/health*//*


    Integer port = 8080;
    String BASE_WEBSOCKET_URI;

    CompletableFuture<String> healthFuture;
    BlockingQueue<String> blockingQueue;
    WebSocketStompClient stompClient;

    @BeforeAll
    @SuppressWarnings("unused")
    void init() {
        BASE_WEBSOCKET_URI = "ws://localhost:" + port + Endpoints.WEBSOCKET;
        healthFuture = new CompletableFuture<>();
        blockingQueue = new LinkedBlockingDeque<>();
        stompClient = new WebSocketStompClient(new SockJsClient(Collections.singletonList(new WebSocketTransport(new StandardWebSocketClient()))));
        stompClient.setMessageConverter(new MappingJackson2MessageConverter());
    }

    @Test
    @DisplayName(value = "websocket health checking")
    void healthChecking() throws InterruptedException, ExecutionException, TimeoutException {
        StompSession stompSession = stompClient
                .connect(BASE_WEBSOCKET_URI + Endpoints.Health.HEALTH, new StompSessionHandlerAdapter() {})
                .get(1, TimeUnit.SECONDS);

        stompSession.subscribe(Endpoints.APP + Endpoints.Measurements.MEASUREMENT, new StompFrameHandler() {
            @Override
            public Type getPayloadType(StompHeaders headers) {
                return String.class;
            }

            @Override
            public void handleFrame(StompHeaders headers, @Nullable Object payload) {
                healthFuture.complete((String) payload);
            }
        });

        stompSession.send(Endpoints.Health.TOPIC_HEALTH, null);

        healthFuture.get(10, TimeUnit.SECONDS);

        assertNotNull(healthFuture.get());
    }

}*/
