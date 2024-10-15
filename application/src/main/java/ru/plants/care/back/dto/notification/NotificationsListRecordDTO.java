package ru.plants.care.back.dto.notification;

import com.fasterxml.jackson.annotation.JsonInclude;
import ru.plants.care.back.repository.model.NotificationEntity;

import java.time.LocalDateTime;

/**
 * DTO for {@link NotificationEntity}
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public record NotificationsListRecordDTO(Long id, LocalDateTime createdAt, String taskName) {
}