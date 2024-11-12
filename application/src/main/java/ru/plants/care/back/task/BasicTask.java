package ru.plants.care.back.task;

import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import ru.plants.care.back.dto.notification.NotificationDTO;
import ru.plants.care.back.mapper.FloristMapper;
import ru.plants.care.back.mapper.TaskMapper;
import ru.plants.care.back.repository.model.FloristEntity;
import ru.plants.care.back.repository.model.TaskRunEntity;
import ru.plants.care.back.services.NotificationService;

@RequiredArgsConstructor
public abstract class BasicTask implements Task {
    private final NotificationService notificationService;
    private final TaskMapper taskMapper;
    private final FloristMapper floristMapper;

    @Override
    public void execute(TaskRunEntity taskRun) {
        var task = taskRun.getTask();
        if (Boolean.TRUE.equals(taskRun.getTask().getSendNotification())) {
            for (FloristEntity florist : task.getPlant().getFlorists()) {
                var notification = NotificationDTO.builder()
                        .taskRun(taskMapper.taskRunEntityToTaskRunDTO(taskRun))
                        .florist(floristMapper.floristEntityToFloristDto(florist))
                        .build();
                notificationService.sendNotification(notification);
            }
        }
    }
}
