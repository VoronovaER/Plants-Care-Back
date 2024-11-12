package ru.plants.care.back.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.constraints.NotEmpty;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.plants.care.back.dto.plant.BasePlantDTO;
import ru.plants.care.back.dto.plant.PlantDTO;
import ru.plants.care.back.dto.planttype.PlantTypeDTO;
import ru.plants.care.back.dto.planttype.PlantTypeListRecordDTO;
import ru.plants.care.back.services.PlantTypeService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/api/v1/plant")
@Schema(name = "Растения", description = "Работа с данными растений")
public class PlantTypeController {
    private final PlantTypeService plantTypeService;


    @Operation(summary = "Получение списка типов растений по части имени")
    @GetMapping(path = "/types/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<PlantTypeListRecordDTO> getPlantTypeListByName(
            @PathVariable @NotEmpty String name
    ) {
        return plantTypeService.getPlantTypeListByName(name);
    }

    @Operation(summary = "Получение списка типов растений")
    @GetMapping(path = "/types", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<PlantTypeListRecordDTO> getPlantTypeList() {
        return plantTypeService.getPlantTypeList();
    }

    @Operation(summary = "Добавление типа растения")
    @PostMapping(path = "/types", consumes = MediaType.APPLICATION_JSON_VALUE)
    public PlantTypeDTO savePlantType(
            @RequestBody PlantTypeDTO plantType
    ) {
        return plantTypeService.savePlantType(plantType);
    }

    @Operation(summary = "Удаление типа растения")
    @DeleteMapping(path = "/types")
    public void deletePlantType(
            @RequestParam Long id
    ) {
        plantTypeService.deletePlantType(id);
    }

    @Operation(summary = "Изменение данных типа растения")
    @PutMapping(path = "/types/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public PlantTypeDTO updatePlantType(
            @PathVariable Long id,
            @RequestBody PlantTypeDTO plant
    ) {
        return plantTypeService.updatePlantType(id, plant);
    }

    @Operation(summary = "Получение информации о типе растения")
    @GetMapping(path = "/types/get/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public PlantTypeDTO getPlantType(@PathVariable Long id) {
        return plantTypeService.getPlantType(id);
    }
}
