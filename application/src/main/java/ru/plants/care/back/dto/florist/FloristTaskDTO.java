package ru.plants.care.back.dto.florist;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import ru.plants.care.back.dto.task.TaskListRecordDTO;
import ru.plants.care.back.dto.task.TaskRunDTO;

import java.time.LocalDateTime;

@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public record FloristTaskDTO(
        LocalDateTime runDateTime,
        TaskListRecordDTO task,
        TaskRunDTO taskRun
) {
}
