package ru.plants.care.back.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.plants.care.back.dto.plant.BasePlantDTO;
import ru.plants.care.back.dto.plant.NewPlantRequestDTO;
import ru.plants.care.back.dto.plant.PlantDTO;
import ru.plants.care.back.dto.plant.PlantListRecordDTO;
import ru.plants.care.back.services.PlantService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/api/v1/plant")
@Schema(name = "Экземпляр растения", description = "Работа с данными растений пользователей")
public class PlantController {
    private final PlantService service;

    @Operation(summary = "Получение списка растений")
    @GetMapping(path = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<PlantListRecordDTO> getPlantList() {
        return service.getPlantList();
    }

    @Operation(summary = "Регистрация растения")
    @PostMapping(path = "/item", consumes = MediaType.APPLICATION_JSON_VALUE)
    public PlantDTO createPlant(
            @RequestBody @Validated NewPlantRequestDTO newPlant
    ) {
        return service.createPlant(newPlant);
    }

    @Operation(summary = "Удаление растения")
    @DeleteMapping(path = "/item/{id}")
    public void deletePlant(
            @PathVariable Long id
    ) {
        service.deletePlant(id);
    }
}
