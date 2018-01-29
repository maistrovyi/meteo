package com.meteo.api.tests.integration;

import com.meteo.api.configs.ApiConstants;
import com.meteo.warehouse.models.Measurement;
import com.meteo.warehouse.repositories.MeasurementRepository;
import com.meteo.api.tests.configs.TestJpaConfiguration;
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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.annotation.Resource;
import java.math.BigDecimal;

import static org.hamcrest.Matchers.hasSize;
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

    private final String INITIAL_ID_SEQ = "1";

    @Resource
    MockMvc mockMvc;

    @Resource
    MeasurementRepository repository;

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

    @Test
    @DisplayName(value = "count test")
    public void testCount() throws Exception {
        mockMvc.perform(get(ApiConstants.Endpoints.API_MEASUREMENT + "count")
                        .contentType(MediaType.APPLICATION_JSON_UTF8))
                        .andDo(print())
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("count", isA(Integer.class)));
    }

    @Test
    @DisplayName(value = "find all test")
    public void testFindAll() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(ApiConstants.Endpoints.API_MEASUREMENT)
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName(value = "find today")
    public void testFindToday() throws Exception {
//        TODO persist some measurements with different datetime
        mockMvc.perform(get(ApiConstants.Endpoints.API_MEASUREMENT + "today")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isOk())
//                TODO expect of content
                .andExpect(jsonPath("$.*", hasSize(1)));
    }

//    TODO implement test find by day

}