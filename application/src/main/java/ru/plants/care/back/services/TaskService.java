package ru.plants.care.back.services;

import ru.plants.care.back.dto.task.NewTaskDTO;
import ru.plants.care.back.dto.task.TaskDTO;
import ru.plants.care.back.dto.task.TaskListRecordDTO;

import java.util.List;

public interface TaskService {
    TaskDTO createTask(Long floristId, NewTaskDTO taskDTO);
    List<TaskListRecordDTO> getTaskList();
    TaskDTO getTask(Long id);
    TaskDTO changeTaskStatus(Long id);

    void deleteTask(Long id);
}
