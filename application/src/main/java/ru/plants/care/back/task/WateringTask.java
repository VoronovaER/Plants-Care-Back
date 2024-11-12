package ru.plants.care.back.task;

import org.springframework.stereotype.Component;
import ru.plants.care.back.mapper.FloristMapper;
import ru.plants.care.back.mapper.TaskMapper;
import ru.plants.care.back.repository.model.TaskRunEntity;
import ru.plants.care.back.services.NotificationService;

@Component
public class WateringTask extends BasicTask {
    public WateringTask(NotificationService notificationService, TaskMapper taskMapper, FloristMapper floristMapper) {
        super(notificationService, taskMapper, floristMapper);
    }

    @Override
    public void execute(TaskRunEntity taskRun) {
        super.execute(taskRun);
    }
}
