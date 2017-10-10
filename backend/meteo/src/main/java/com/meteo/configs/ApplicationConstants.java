package com.meteo.configs;

import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class ApplicationConstants {

    @NoArgsConstructor(access = PRIVATE)
    public static class Endpoints {

        public static final String API = "/api";
        public static final String SOCKET = "/socket";
        public static final String TOPIC = "/topic";

        public static final String MEASUREMENT = "/measurement";
        public static final String API_MEASUREMENT = API + MEASUREMENT;

    }

}