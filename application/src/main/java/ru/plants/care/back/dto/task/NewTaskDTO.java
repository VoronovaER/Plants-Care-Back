package ru.plants.care.back.dto.task;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;


/**
 * DTO for {@link ru.plants.care.back.repository.model.TaskEntity}
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@Validated
public class NewTaskDTO {
    private Boolean enabled;
    @NotNull
    private TaskType type;
    @NotEmpty
    private String name;
    @NotEmpty
    private String description;
    @NotNull
    private String plantName;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    @NotNull
    private TaskPeriod period;
    private Boolean sendNotification;
}
