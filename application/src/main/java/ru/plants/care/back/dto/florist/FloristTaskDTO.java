package ru.plants.care.back.dto.florist;

import ru.plants.care.back.dto.task.TaskListRecordDTO;

import java.time.LocalDateTime;

public record FloristTaskDTO(
        LocalDateTime localDateTime,
        TaskListRecordDTO task
) {
}
