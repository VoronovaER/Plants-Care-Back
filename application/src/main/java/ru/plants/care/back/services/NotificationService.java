package ru.plants.care.back.services;

import ru.plants.care.back.dto.notification.NotificationDTO;
import ru.plants.care.back.dto.notification.NotificationsListRecordDTO;

import java.time.LocalDateTime;
import java.util.List;

public interface NotificationService {
    NotificationDTO sendNotification(NotificationDTO notification);
    NotificationDTO getNotification(Long id);
    List<NotificationsListRecordDTO> getNotificationList(Long floristId, LocalDateTime startDateTime, LocalDateTime endDateTime);
    void deleteNotification(Long id);
}
