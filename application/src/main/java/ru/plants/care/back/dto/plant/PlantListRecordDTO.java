package ru.plants.care.back.dto.plant;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;


/**
 * DTO for {@link ru.plants.care.back.repository.model.PlantEntity}
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@EqualsAndHashCode(callSuper = true)
@Data
public class PlantListRecordDTO extends BasePlantDTO {
    private Long id;
    private String plantType;
    private Long plantTypeId;
}
