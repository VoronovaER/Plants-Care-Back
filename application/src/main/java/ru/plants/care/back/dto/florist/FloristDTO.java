package ru.plants.care.back.dto.florist;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * DTO for {@link ru.plants.care.back.repository.model.FloristEntity}
 */

@EqualsAndHashCode(callSuper = true)
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FloristDTO extends BaseFloristDTO {
    private Long id;
    private int plantsQuantity;
}
