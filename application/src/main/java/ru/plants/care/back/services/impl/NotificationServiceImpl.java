package ru.plants.care.back.services.impl;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.plants.care.back.dto.notification.NotificationDTO;
import ru.plants.care.back.dto.notification.NotificationsListRecordDTO;
import ru.plants.care.back.exception.ItemNotFoundException;
import ru.plants.care.back.mapper.NotificationMapper;
import ru.plants.care.back.repository.FloristRepository;
import ru.plants.care.back.repository.NotificationRepository;
import ru.plants.care.back.services.NotificationService;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {
    private final FloristRepository floristRepository;
    private final NotificationRepository repository;
    private final NotificationMapper mapper;

    @Override
    public NotificationDTO saveNotification(NotificationDTO notification) {
        return mapper.notificationEntityToNotificationDTO(repository.save(mapper.notificationDTOtoNotificationEntity(notification)));
    }

    @Override
    public NotificationDTO getNotification(Long id) {
        return mapper.notificationEntityToNotificationDTO(repository.findById(id).orElse(null));
    }

    @Override
    public List<NotificationsListRecordDTO> getNotificationList(Long floristId, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        var florist = floristRepository.findById(floristId);
        if (florist.isPresent()) {
            return mapper.notificationEntityToNotificationListRecordDTO(repository.findByFloristAndCreatedAtBetween(florist.get(), startDateTime, endDateTime));
        } else {
            throw new ItemNotFoundException("Флорист с id =  " + floristId + " не найден");
        }
    }

    @Override
    public void deleteNotification(Long id) {
        repository.deleteById(id);
    }
}
