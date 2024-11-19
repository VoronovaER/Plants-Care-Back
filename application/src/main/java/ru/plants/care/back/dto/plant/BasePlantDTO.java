package ru.plants.care.back.dto.plant;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.validation.annotation.Validated;


/**
 * DTO for {@link ru.plants.care.back.repository.model.PlantEntity}
 */
@Data
@NoArgsConstructor
@Validated
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class BasePlantDTO {
    @NotEmpty
    private String name;
    @NotEmpty
    private String place;
    private String url;
    private String description;
}
