package ru.plants.care.back.dto.task;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;


/**
 * DTO for {@link ru.plants.care.back.repository.model.TaskEntity}
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
//@SuperBuilder
@NoArgsConstructor
public class BaseTaskDTO {
    private LocalDateTime createdAt;
    private boolean enabled;
    private TaskType type;
    private String name;
}
