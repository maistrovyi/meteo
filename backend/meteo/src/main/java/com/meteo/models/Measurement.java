package com.meteo.models;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

@Data
@Builder(builderMethodName = "of")
@Document(collection = "measurements")
public final class Measurement {

    @Id
    private String id;

    private BigDecimal temperature;

    private BigDecimal humidity;

    private BigDecimal pressure;

    @CreatedDate
    private ZonedDateTime time;

}