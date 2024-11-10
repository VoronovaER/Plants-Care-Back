package ru.plants.care.back.dto.plant;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.SuperBuilder;
import ru.plants.care.back.dto.florist.FloristDTO;
import ru.plants.care.back.dto.planttype.PlantTypeDTO;
import ru.plants.care.back.dto.task.TaskDTO;
import ru.plants.care.back.dto.task.TaskListRecordDTO;
import ru.plants.care.back.repository.model.TaskEntity;

import java.util.List;


/**
 * DTO for {@link ru.plants.care.back.repository.model.PlantEntity}
 */
//@JsonInclude(JsonInclude.Include.NON_NULL)
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlantDTO extends BasePlantDTO {
    private Long id;
    private PlantTypeDTO plantType;
    private List<FloristDTO> florists;
    private List<TaskListRecordDTO> tasks;
}
