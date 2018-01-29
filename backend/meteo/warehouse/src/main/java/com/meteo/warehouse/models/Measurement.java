package com.meteo.warehouse.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;

import javax.persistence.*;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import java.beans.ConstructorProperties;
import java.math.BigDecimal;
import java.time.ZonedDateTime;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Data
@Entity
@Immutable
@NoArgsConstructor
@Table(name = "measurements")
@JsonInclude(value = NON_NULL)
@JsonPropertyOrder(value = {"id", "temperature", "humidity", "pressure"})
@JsonIgnoreProperties(ignoreUnknown = true)
public class Measurement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @DecimalMin(value = "-25.00")
    @DecimalMax(value = "30.00")
    @Column(name = "temperature", nullable = false)
    private BigDecimal temperature;

    @DecimalMin(value = "0.00")
    @DecimalMax(value = "100.00")
    @Column(name = "humidity", nullable = false)
    private BigDecimal humidity;

    @DecimalMin(value = "560.00")
    @DecimalMax(value = "771.00")
    @Column(name = "pressure", nullable = false)
    private BigDecimal pressure;

    @JsonIgnore
    @Column(name = "time", nullable = false)
    private ZonedDateTime time;

    @Builder(builderMethodName = "of")
    @SuppressWarnings(value = "unused")
    @ConstructorProperties(value = {"temperature", "humidity", "pressure"})
    public Measurement(BigDecimal temperature, BigDecimal humidity, BigDecimal pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        this.time = ZonedDateTime.now();
    }

}