package ru.plants.care.back.dto.task;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import ru.plants.care.back.dto.plant.PlantDTO;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
//@SuperBuilder
@NoArgsConstructor
public class TaskDTO extends BaseTaskDTO {
    private Long id;
    private String description;
    private PlantDTO plant;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private TaskPeriod period;
}
