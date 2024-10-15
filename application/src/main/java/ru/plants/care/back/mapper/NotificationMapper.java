package ru.plants.care.back.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import ru.plants.care.back.dto.notification.NotificationDTO;
import ru.plants.care.back.dto.notification.NotificationsListRecordDTO;
import ru.plants.care.back.repository.model.NotificationEntity;

import java.util.List;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        componentModel = "spring",
        uses = {FloristMapper.class})
public interface NotificationMapper {
    NotificationDTO notificationEntityToNotificationDTO(NotificationEntity value);

    List<NotificationsListRecordDTO> notificationEntityToNotificationListRecordDTO(List<NotificationEntity> notificationList);

    NotificationEntity notificationDTOtoNotificationEntity(NotificationDTO value);

    NotificationEntity toEntity(NotificationDTO notificationDTO);

    NotificationDTO toDto(NotificationEntity notificationEntity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    NotificationEntity partialUpdate(NotificationDTO notificationDTO, @MappingTarget NotificationEntity notificationEntity);
}
