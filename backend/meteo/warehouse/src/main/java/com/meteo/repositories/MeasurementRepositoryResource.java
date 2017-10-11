package com.meteo.repositories;

import com.meteo.models.Measurement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface MeasurementRepositoryResource extends JpaRepository<Measurement, Long> {  }