package com.meteo.api.tests.integration;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.TestInstance.Lifecycle;

@Slf4j
@Tag(value = "integration-fast")
@TestInstance(Lifecycle.PER_CLASS)
@Profile(value = "integration-test")
@RunWith(value = JUnitPlatform.class)
@ActiveProfiles(value = "integration-test")
@ExtendWith(value = SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class DefaultMessageListenerTest {

//    TODO implement integration tests

}
