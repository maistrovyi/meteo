package com.meteo.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.annotation.Nonnegative;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.beans.ConstructorProperties;
import java.math.BigDecimal;
import java.time.ZonedDateTime;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Data
@Entity
@NoArgsConstructor
@Table(name = "measurements")
@JsonInclude(value = NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Measurement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "GROUP_ID")
    private Long id;

    @NotNull
    @Column(name = "temperature", nullable = false)
    private BigDecimal temperature;

    @NotNull
    @Column(name = "humidity", nullable = false)
    @Nonnegative
    private BigDecimal humidity;

    @NotNull
    @Column(name = "pressure", nullable = false)
    @Nonnegative
    private BigDecimal pressure;

    @Column(name = "time", nullable = false)
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