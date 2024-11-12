package ru.plants.care.back.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import ru.plants.care.back.dto.notification.TaskRunDTO;
import ru.plants.care.back.dto.task.NewTaskDTO;
import ru.plants.care.back.dto.task.TaskDTO;
import ru.plants.care.back.dto.task.TaskListRecordDTO;
import ru.plants.care.back.repository.model.TaskEntity;
import ru.plants.care.back.repository.model.TaskRunEntity;

import java.util.List;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        componentModel = "spring",
        uses = {FloristMapper.class, PlantMapper.class})
public interface TaskMapper {
    TaskEntity newTaskDTOtoTaskEntity(NewTaskDTO taskDTO);

    TaskDTO taskEntityToTaskDTO(TaskEntity taskEntity);

    List<TaskListRecordDTO> taskEntityToTaskListRecordDTO(List<TaskEntity> all);
    @Mapping(source = "taskEntity.plant.name", target = "plantName")
    @Mapping(source = "taskEntity.plant.plantType", target = "plantTypeName")
    TaskListRecordDTO taskEntityToTaskListRecordDTO(TaskEntity taskEntity);
    TaskRunDTO taskRunEntityToTaskRunDTO(TaskRunEntity taskRunEntity);
}
