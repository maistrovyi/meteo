package com.meteo.models;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.NotNull;
import java.beans.ConstructorProperties;
import java.math.BigDecimal;
import java.time.ZonedDateTime;

@Data
@Document(collection = "measurements")
public final class Measurement {

    @Id
    private String id;

    @NotNull
    @Field("temperature")
    private BigDecimal temperature;

    @NotNull
    @Field("humidity")
    private BigDecimal humidity;

    @NotNull
    @Field("pressure")
    private BigDecimal pressure;

    @CreatedDate
    @Field("time")
    private ZonedDateTime time;

    @ConstructorProperties(value = {"temperature", "humidity", "pressure"})
    @Builder(builderMethodName = "of")
    public Measurement(BigDecimal temperature, BigDecimal humidity, BigDecimal pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        this.time = ZonedDateTime.now();
    }

}