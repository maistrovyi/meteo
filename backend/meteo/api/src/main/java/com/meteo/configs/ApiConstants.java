package com.meteo.configs;

import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class ApiConstants {

    @NoArgsConstructor(access = PRIVATE)
    public static class Endpoints {

        public static final String APP = "/app";
        public static final String WEBSOCKET = "/websocket";
        public static final String TOPIC = "/topic";

        @NoArgsConstructor(access = PRIVATE)
        public static class Measurements {

            public static final String MEASUREMENT = "/measurement";
            public static final String TOPIC_MEASUREMENT = TOPIC + MEASUREMENT;

        }

        @NoArgsConstructor(access = PRIVATE)
        public static class Health {

            public static final String HEALTH = "/health";
            public static final String TOPIC_HEALTH = TOPIC + HEALTH;

        }

    }

}