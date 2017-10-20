package com.meteo.tests.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.meteo.models.Measurement;
import com.meteo.repositories.MeasurementRepository;
import com.meteo.tests.TestJpaConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import javax.annotation.Resource;
import java.math.BigDecimal;

import static com.meteo.configs.ApiConstants.Endpoints.Measurements.API_MEASUREMENT;
import static org.hamcrest.Matchers.isA;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
@WebMvcTest
@AutoConfigureMockMvc
@Import(value = TestJpaConfiguration.class)
@RunWith(value = JUnitPlatform.class)
@Tag(value = "integration-fast")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Profile(value = "integration-test")
@ActiveProfiles(value = "integration-test")
@ExtendWith(value = SpringExtension.class)
public class MeasurementControllerTest {

    @Resource
    MockMvc mockMvc;

    @Resource
    MeasurementRepository repository;

    @Resource
    ObjectMapper objectMapper;

    Measurement dto;

    @BeforeAll
    @SuppressWarnings(value = "unused")
    public void init() {
        dto = Measurement.of()
                .temperature(BigDecimal.valueOf(3.0))
                .humidity(BigDecimal.valueOf(58.3))
                .pressure(BigDecimal.valueOf(768.5))
                .build();
        repository.save(dto);
    }

    @Disabled
    @Test
    @DisplayName(value = "count test")
    public void testCount() throws Exception {
        mockMvc.perform(get(API_MEASUREMENT + "/count")
                        .contentType(MediaType.APPLICATION_JSON_UTF8))
                        .andDo(print())
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("count", isA(Integer.class)));
    }

    @Disabled
    @Test
    @DisplayName(value = "find all test")
    public void testFindAll() throws Exception {
        mockMvc.perform(get(API_MEASUREMENT)
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isOk());
    }

}