package ru.plants.care.back.dto.notification;

import com.fasterxml.jackson.annotation.JsonInclude;
import ru.plants.care.back.dto.task.TaskDTO;

import java.time.LocalDateTime;

/**
 * DTO for {@link ru.plants.care.back.repository.model.TaskRunEntity}
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public record TaskRunDTO(Long id, TaskDTO task, LocalDateTime start, LocalDateTime completed) {
}