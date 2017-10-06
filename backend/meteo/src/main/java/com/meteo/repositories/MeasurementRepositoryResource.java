package com.meteo.repositories;

import com.meteo.models.Measurement;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface MeasurementRepositoryResource extends ReactiveMongoRepository<Measurement, String> {  }