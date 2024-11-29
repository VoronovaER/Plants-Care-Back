package ru.plants.care.back.services;

import ru.plants.care.back.dto.task.TaskRunStatus;

public interface TaskRunService {
    public void changeStatus(TaskRunStatus status, Long id);
}
