package ru.plants.care.back.services.impl;


import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class NotificationServiceImpl implements NotificationService {
    private final FloristRepository floristRepository;
    private final NotificationRepository repository;
    private final NotificationMapper mapper;
    private final FirebaseMessaging firebaseMessaging;

    @Override
    public NotificationDTO sendNotification(NotificationDTO notification) {
        var floristOpt = floristRepository.findById(notification.florist().getId());
        floristOpt.ifPresent(florist -> {
            if (florist.getFirebaseToken() != null) {
                Message msg = Message.builder()
                        .setToken(florist.getFirebaseToken())
                        .putData("title", "Plants Care")
                        .putData("body", notification.taskRun().task().getName())
                        .build();
                try {
                    firebaseMessaging.send(msg);
                } catch (FirebaseMessagingException e) {
                    log.error("Ошибка отправки уведомления клиенту {}: {}/{}", florist.getEmail(), e.getClass().getName(), e.getMessage());
                }
            }
        });

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
