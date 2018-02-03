package com.meteo.tractor.configs;

import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public final class ApplicationConstants {

    @NoArgsConstructor(access = PRIVATE)
    public static class TimeDuration {

        public static final LocalDateTime START_AT = LocalDateTime.of(
                LocalDate.of(2008, 1, 1), LocalTime.of(0, 0, 0)
        );

        public static final long HOURS_DELTA = 4L;

    }

    @NoArgsConstructor(access = PRIVATE)
    public final static class Weather {

        public static final Double KIEV_CENTER_LONGITUDE = 30.5238000D;
        public static final Double KIEV_CENTER_LATITUDE = 50.4546600D;

        public static final String API_KEY = "ef788f4f2bce6a8bbe333f7e18e9146a";
        public static final int REQUESTS_LIMIT = 1000;

    }


}