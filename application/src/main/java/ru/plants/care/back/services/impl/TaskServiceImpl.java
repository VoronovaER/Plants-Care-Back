package ru.plants.care.back.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.plants.care.back.dto.task.NewTaskDTO;
import ru.plants.care.back.dto.task.TaskDTO;
import ru.plants.care.back.dto.task.TaskListRecordDTO;
import ru.plants.care.back.exception.ItemNotFoundException;
import ru.plants.care.back.mapper.TaskMapper;
import ru.plants.care.back.repository.PlantRepository;
import ru.plants.care.back.repository.TaskRepository;
import ru.plants.care.back.services.TaskService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;
    private final PlantRepository plantRepository;

    @Override
    public TaskDTO createTask(NewTaskDTO taskDTO) {
        var plantEntity = plantRepository.findById(taskDTO.getPlantId());
        if (plantEntity.isEmpty()) {
            throw new ItemNotFoundException("Plant not found" + taskDTO.getPlantId());
        }
        var taskEntity = taskMapper.newTaskDTOtoTaskEntity(taskDTO);
        taskEntity.setPlant(plantEntity.get());
        taskEntity.setNextRun(taskEntity.getStartDate());
        return taskMapper.taskEntityToTaskDTO(taskRepository.save(taskEntity));
    }

    @Override
    public List<TaskListRecordDTO> getTaskList() {
        return taskMapper.taskEntityToTaskListRecordDTO(taskRepository.findAll());
    }

    @Override
    public TaskDTO getTask(Long id) {
        var taskEntity = taskRepository.findById(id);
        if (taskEntity.isEmpty()) {
            throw new ItemNotFoundException("Task not found" + id);
        }
        return taskMapper.taskEntityToTaskDTO(taskEntity.get());
    }

    @Override
    public TaskDTO changeTaskStatus(Long id) {
        var taskEntity = taskRepository.findById(id);
        if (taskEntity.isEmpty()) {
            throw new ItemNotFoundException("Task not found" + id);
        }
        taskEntity.get().setEnabled(!taskEntity.get().getEnabled());
        return taskMapper.taskEntityToTaskDTO(taskRepository.save(taskEntity.get()));
    }
}
