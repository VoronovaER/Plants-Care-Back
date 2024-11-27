package ru.plants.care.back.dto.task;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public record TaskRunDTO(
        Long id,
        LocalDateTime startAt,
        LocalDateTime endAt,
        TaskRunStatus status,
        String statusDescription
) {}
