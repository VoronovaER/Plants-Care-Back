package ru.plants.care.back.dto.florist;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

/**
 DTO for {@link ru.plants.care.back.repository.model.FloristEntity}
 **/
@Data
@NoArgsConstructor
@Validated
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseFloristDTO {
    @NotEmpty
    private String name;
    @NotEmpty
    private String avatar;
    @NotEmpty
    private String email;
}
