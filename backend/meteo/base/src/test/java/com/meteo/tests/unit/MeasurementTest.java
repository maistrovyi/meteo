package com.meteo.tests.unit;

import com.google.common.collect.Sets;
import com.meteo.models.Measurement;
import com.meteo.repositories.MeasurementRepositoryResource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.annotation.Resource;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.TestInstance.Lifecycle;

@Slf4j
@DataJpaTest
@Tag(value = "slow")
@TestInstance(Lifecycle.PER_CLASS)
@RunWith(value = JUnitPlatform.class)
@ExtendWith(value = SpringExtension.class)
class MeasurementTest {

    @Resource
    private MeasurementRepositoryResource repository;

    @BeforeAll
    @SuppressWarnings(value = "unused")
    void init() {
        repository.saveAll(Sets.newHashSet(
                Measurement.of()
                        .temperature(BigDecimal.valueOf(13.0))
                        .humidity(BigDecimal.valueOf(66.0))
                        .pressure(BigDecimal.valueOf(746))
                        .build(),
                Measurement.of()
                        .temperature(BigDecimal.valueOf(1.0))
                        .humidity(BigDecimal.valueOf(90))
                        .pressure(BigDecimal.valueOf(745))
                        .build()
        ));
        log.info("Successfully persisted default entities data for integration tests.");
    }

    @Test
    @DisplayName(value = "check that initial measurement count is 2")
    void initialMeasurementCountChecking() {
        assertEquals(2, repository.count());
    }

    @Test
    @DisplayName(value = "check that invalid measurement throw exception")
    void asd() {
        fail("Needs to implement validation!");
        Measurement measurement = new Measurement(BigDecimal.valueOf(50.00), BigDecimal.valueOf(2100.00), BigDecimal.valueOf(0.00));
        measurement.setHumidity(BigDecimal.valueOf(10_000.00));
//        assertAll(() -> {
//            IllegalArgumentException nonNegativeIAException = assertThrows(IllegalArgumentException.class, () ->
//
////            assertEquals(NON_NEGATIVE_MESSAGE, nonNegativeIAException.getMessage());
//        });
    }

}