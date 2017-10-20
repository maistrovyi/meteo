package com.meteo.repositories;

import com.meteo.models.Measurement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import java.time.ZonedDateTime;
import java.util.List;

@Repository
public interface MeasurementRepository extends JpaRepository<Measurement, Long> {

    List<Measurement> findAllByTimeBetween(@NotNull ZonedDateTime start, @NotNull ZonedDateTime end);

}