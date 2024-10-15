package ru.plants.care.back.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.plants.care.back.repository.model.FloristEntity;
import ru.plants.care.back.repository.model.NotificationEntity;

import java.time.LocalDateTime;
import java.util.List;

public interface NotificationRepository extends JpaRepository<NotificationEntity, Long> {
    List<NotificationEntity> findByFloristAndCreatedAtBetween(FloristEntity florist, LocalDateTime startDateTime, LocalDateTime endDateTime);
}
