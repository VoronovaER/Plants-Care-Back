package ru.plants.care.back.task;

import ru.plants.care.back.repository.model.TaskRunEntity;

public interface Task {
    void execute(TaskRunEntity taskRun);
}
