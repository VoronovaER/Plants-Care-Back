package ru.plants.care.back.dto.task;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

/**
 * DTO for {@link ru.plants.care.back.repository.model.TaskEntity}
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@EqualsAndHashCode(callSuper = true)
@Data
//@SuperBuilder
public class TaskListRecordDTO extends BaseTaskDTO {
    private Long id;
    private String plantTypeName;
    private String plantName;
    private TaskPeriod period;
}
