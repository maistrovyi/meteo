package com.meteo.tractor.converter;

import lombok.NoArgsConstructor;
import tk.plogitech.darksky.forecast.model.Forecast;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public final class ForecastToLineConverter {

    public static final Function<Forecast, Set<String>> MAP_TO_DAY_LINE = forecast ->
        forecast.getHourly().getData().stream().sequential().map(dataPoint -> String.format("%s, %f, %f, %s, %f, %f, %f, %f, %f, %d, %f, %f, %f, %f, %s%n",
                dataPoint.getSummary(), dataPoint.getPrecipIntensity(),
                dataPoint.getPrecipProbability(), dataPoint.getPrecipType(),
                dataPoint.getTemperature(), dataPoint.getApparentTemperature(),
                dataPoint.getDewPoint(), dataPoint.getHumidity(),
                dataPoint.getWindSpeed(), dataPoint.getWindBearing(),
                dataPoint.getPressure(), dataPoint.getCloudCover(),
                dataPoint.getOzone(), dataPoint.getVisibility(),
                dataPoint.getTime())
        ).collect(Collectors.toCollection(LinkedHashSet::new));

}
