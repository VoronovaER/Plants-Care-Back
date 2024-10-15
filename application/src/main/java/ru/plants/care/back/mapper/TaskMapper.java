package ru.plants.care.back.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import ru.plants.care.back.dto.task.NewTaskDTO;
import ru.plants.care.back.dto.task.TaskDTO;
import ru.plants.care.back.dto.task.TaskListRecordDTO;
import ru.plants.care.back.repository.model.TaskEntity;

import java.util.List;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        componentModel = "spring",
        uses = {FloristMapper.class, PlantMapper.class})
public interface TaskMapper {
    TaskEntity newTaskDTOtoTaskEntity(NewTaskDTO taskDTO);

    TaskDTO taskEntityToTaskDTO(TaskEntity taskEntity);

    List<TaskListRecordDTO> taskEntityToTaskListRecordDTO(List<TaskEntity> all);
}
