package ru.plants.care.back.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.plants.care.back.repository.model.ImageEntity;
import ru.plants.care.back.repository.model.NotificationEntity;

import java.awt.*;

public interface ImageRepository extends JpaRepository<ImageEntity, Long> {
    ImageEntity findByFileId(String fileId);
}
