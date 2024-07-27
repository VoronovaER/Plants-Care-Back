package ru.plants.care.back.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorDTO {
    public String type;
    public String detail;
}
