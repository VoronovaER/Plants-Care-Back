package ru.plants.care.back.dto.planttype;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PlantTypeListRecordDTO extends BasePlantTypeDTO {
    private Long id;
}
