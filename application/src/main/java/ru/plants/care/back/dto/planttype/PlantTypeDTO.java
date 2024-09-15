package ru.plants.care.back.dto.planttype;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PlantTypeDTO extends BasePlantTypeDTO {
    private String latinName;
    private String description;
    private String blossom;
    private String sumWatering;
    private String winWatering;
    private String light;
    private int sumTempMin;
    private int sumTempMax;
    private int winTempMin;
    private int winTempMax;
    private String types;
    private String fertilize;
    private String transplant;
}
