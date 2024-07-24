package ru.plants.care.back.dto.planttype;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BasePlantTypeDTO {
    private String name;
    private String url;
}
