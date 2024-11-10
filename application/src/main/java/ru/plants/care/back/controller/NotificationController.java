package ru.plants.care.back.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.plants.care.back.dto.notification.NotificationDTO;
import ru.plants.care.back.dto.notification.NotificationsListRecordDTO;
import ru.plants.care.back.services.NotificationService;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/api/v1/notifications")
@Schema(name = "Уведомления", description = "Работа с данными уведомлений")
public class NotificationController {
    private final NotificationService service;

    @Operation(summary = "Чтение данных события")
    @GetMapping(path = "/{notificationId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public NotificationDTO getNotification(@PathVariable Long notificationId) {
        return service.getNotification(notificationId);
    }

    @Operation(summary = "Получение списка событий")
    @GetMapping(path = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<NotificationsListRecordDTO> getNotificationsList(
            @Parameter(description = "Идентификатор флориста")
            @RequestParam Long floristId,
            @Parameter(description = "Дата начала периода")
            @RequestParam(required = false) LocalDateTime startDateTime,
            @Parameter(description = "Дата окончания периода")
            @RequestParam(required = false) LocalDateTime endDateTime) {
        return service.getNotificationList(floristId, startDateTime, endDateTime);
    }

    @Operation(summary = "Удаление нотификации")
    @DeleteMapping
    public void deleteNotification(
            @RequestParam Long id
    ) {
        service.deleteNotification(id);
    }

}
