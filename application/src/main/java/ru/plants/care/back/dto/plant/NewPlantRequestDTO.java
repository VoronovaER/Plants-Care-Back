package ru.plants.care.back.dto.plant;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;


/**
 * DTO for {@link ru.plants.care.back.repository.model.PlantEntity}
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@Validated
public class NewPlantRequestDTO extends BasePlantDTO {
    @NotNull
    private Long plantTypeId;
    @NotNull
    private Long floristId;
}
