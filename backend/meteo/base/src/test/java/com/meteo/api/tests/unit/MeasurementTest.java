package com.meteo.api.tests.unit;

import com.meteo.warehouse.models.Measurement;
import com.meteo.warehouse.repositories.MeasurementRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.TestInstance.Lifecycle;

@DataJpaTest
@Tag(value = "unit-fast")
@TestInstance(Lifecycle.PER_CLASS)
@RunWith(value = JUnitPlatform.class)
@ExtendWith(value = SpringExtension.class)
class MeasurementTest {

    @Resource
    private MeasurementRepository repository;

    @BeforeAll
    @SuppressWarnings(value = "unused")
    void init() {
        repository.saveAll(Set.of(
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