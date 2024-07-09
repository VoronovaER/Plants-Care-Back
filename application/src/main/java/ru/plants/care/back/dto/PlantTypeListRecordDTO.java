package ru.plants.care.back.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PlantTypeListRecordDTO extends BasePlantTypeDTO{
    private Long id;
}
