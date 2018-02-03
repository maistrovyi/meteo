package com.meteo.tractor.service;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

import static com.meteo.tractor.configs.ApplicationConstants.TimeDuration.START_AT;
import static java.io.File.separator;

@Slf4j
@Service
public final class DatasetService {

    private static final String DATASET_NAME = "dataset.csv";
    private static final String DATASET_DIR = System.getProperty("user.dir") + separator + "data" + separator;
    private static final String DATASET_PATH = DATASET_DIR + DATASET_NAME;

    @SneakyThrows
    @PostConstruct
    @SuppressWarnings("ResultOfMethodCallIgnored")
    private void init() {
        if (!Files.exists(Paths.get(DATASET_PATH))) {
            new File(DATASET_DIR).mkdir();
            new FileWriter(DATASET_PATH, true);
            log.info("File '{}' successfully created!", DATASET_NAME);
        }
        findLatestDate();
    }

    @SneakyThrows
    @SuppressWarnings("ConstantConditions")
    public LocalDateTime findLatestDate() {
        File dataset = new File(DATASET_PATH);
        BufferedReader bufferedReader = new BufferedReader(new FileReader(dataset));
        String latest = null;
        while (Objects.nonNull(bufferedReader.readLine())) {
            latest = bufferedReader.readLine();
        }
        if (Objects.isNull(latest)) {
            return START_AT;
        }
        return LocalDateTime.parse(latest.substring(latest.lastIndexOf(", ") + 2, latest.length() - 1));
    }

    @SneakyThrows
    public void append(Set<String> set) {
        FileWriter fileWriter = new FileWriter(DATASET_PATH, true);
        set.stream().sequential().forEach(forecast -> {
            try {
                fileWriter.write(forecast);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        fileWriter.close();
    }

}