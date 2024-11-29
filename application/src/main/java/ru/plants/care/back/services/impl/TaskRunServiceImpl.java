package ru.plants.care.back.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.plants.care.back.dto.task.TaskRunStatus;
import ru.plants.care.back.repository.TaskRunRepository;
import ru.plants.care.back.repository.model.TaskRunEntity;
import ru.plants.care.back.services.TaskRunService;

@Service
@RequiredArgsConstructor

public class TaskRunServiceImpl implements TaskRunService {
    private final TaskRunRepository taskRunRepository;
    @Override
    public void changeStatus(TaskRunStatus status, Long id) {
        TaskRunEntity taskRunEntity = taskRunRepository.findById(id).get();
        taskRunEntity.setStatus(status);
        taskRunRepository.save(taskRunEntity);
    }
}
