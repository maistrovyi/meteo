package com.meteo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;

public final class CountDto {

    @SuppressWarnings("WeakerAccess")
    @JsonProperty(value = "count")
    public final Long count;

    private CountDto(Long count) {
        this.count = count;
    }

    public static CountDto toDto(@NotNull Long count) {
        return new CountDto(count);
    }

}