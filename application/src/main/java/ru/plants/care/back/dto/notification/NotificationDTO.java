package ru.plants.care.back.dto.notification;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import ru.plants.care.back.dto.florist.FloristDTO;
import ru.plants.care.back.repository.model.NotificationEntity;

import java.time.LocalDateTime;

/**
 * DTO for {@link NotificationEntity}
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public record NotificationDTO(Long id, LocalDateTime createdAt, FloristDTO florist, TaskRunDTO taskRun,
                              LocalDateTime delivered, LocalDateTime read) {
}