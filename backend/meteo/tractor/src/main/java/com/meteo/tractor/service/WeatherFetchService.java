package com.meteo.tractor.service;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tk.plogitech.darksky.api.jackson.DarkSkyJacksonClient;
import tk.plogitech.darksky.forecast.APIKey;
import tk.plogitech.darksky.forecast.ForecastRequest;
import tk.plogitech.darksky.forecast.ForecastRequestBuilder;
import tk.plogitech.darksky.forecast.GeoCoordinates;
import tk.plogitech.darksky.forecast.model.Forecast;
import tk.plogitech.darksky.forecast.model.Latitude;
import tk.plogitech.darksky.forecast.model.Longitude;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;

import static com.meteo.tractor.configs.ApplicationConstants.Weather.*;
import static com.meteo.tractor.converter.ForecastToLineConverter.MAP_TO_DAY_LINE;
import static java.util.concurrent.TimeUnit.NANOSECONDS;
import static java.util.concurrent.TimeUnit.SECONDS;
import static tk.plogitech.darksky.forecast.ForecastRequestBuilder.Block.*;

@Slf4j
@Service
@RequiredArgsConstructor
public final class WeatherFetchService {

    private static final AtomicInteger REQUEST_COUNTER = new AtomicInteger(100);
    private static final Set<String> WEATHER_PARTITION = new LinkedHashSet<>();

    private static Supplier<ForecastRequest> requestSupplier(LocalDateTime at) {
        return () -> new ForecastRequestBuilder()
                .key(new APIKey(API_KEY))
                .location(new GeoCoordinates(
                        new Longitude(KIEV_CENTER_LONGITUDE), new Latitude(KIEV_CENTER_LATITUDE)))
                .language(ForecastRequestBuilder.Language.en)
                .time(at.toInstant(ZoneOffset.UTC))
                .units(ForecastRequestBuilder.Units.si)
                .exclude(currently, minutely, daily, alerts, flags)
                .extendHourly()
                .build();
    }

    private final DarkSkyJacksonClient client;
    private final DatasetService datasetService;

    @SneakyThrows
    public void fetch() {
        long before = System.nanoTime();
        LocalDateTime at = datasetService.findLatestDate();
        while (REQUEST_COUNTER.get() <= REQUESTS_LIMIT) {
            if (!LocalDate.now().isEqual(at.toLocalDate())) {
                log.debug("Fetching weather at '{}'", at);
                Forecast forecast = client.forecast(requestSupplier(at).get());
                Set<String> set = MAP_TO_DAY_LINE.apply(forecast);
                WEATHER_PARTITION.addAll(set);
                REQUEST_COUNTER.incrementAndGet();
                at = at.plusDays(1);
            }
        }
        datasetService.append(WEATHER_PARTITION);
        long after = System.nanoTime();
        long time = SECONDS.convert(after - before, NANOSECONDS);
        log.info("Fetching successfully finished in '{}' seconds!", time);
    }

}