package ru.plants.care.back.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.plants.care.back.dto.florist.BaseFloristDTO;
import ru.plants.care.back.dto.florist.FloristDTO;
import ru.plants.care.back.dto.plant.PlantListRecordDTO;
import ru.plants.care.back.services.FloristService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/api/v1")
@Schema(name = "Растения", description = "Работа с данными флориста")
public class FloristController {
    private final FloristService service;

    @Operation(summary = "Запись данных флориста")
    @PostMapping(path = "/florist", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public BaseFloristDTO saveFlorist(
            @RequestBody BaseFloristDTO florist
    ) {
        return service.saveFlorist(florist);
    }

    @Operation(summary = "Получение списка флористов")
    @GetMapping(path = "/florist", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<FloristDTO> getFloristList() {
        return service.getFloristList();
    }

    @Operation(summary = "Удаление флориста")
    @DeleteMapping(path = "/florist")
    public void deleteFlorist(
            @RequestParam Long id
    ) {
        service.deleteFlorist(id);
    }

    @Operation(summary = "Добавление растения к флористу")
    @PutMapping(path = "/florist/{floristId}/plant/{plantId}")
    public void addPlant(
            @PathVariable Long floristId,
            @PathVariable Long plantId) {
        service.addPlant(floristId, plantId);
    }

    @Operation(summary = "Изменение данных флориста")
    @PutMapping(path = "/florist/{floristId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public BaseFloristDTO updateFlorist(
            @PathVariable Long floristId,
            @RequestBody BaseFloristDTO florist
    ) {
        return service.updateFlorist(floristId, florist);
    }
    @Operation(summary  =  "Получение списка растений в флориста")
    @GetMapping(path  =  "/florist/{floristId}/plant", produces  = MediaType.APPLICATION_JSON_VALUE)
    public List<PlantListRecordDTO> getPlantList(
            @PathVariable Long floristId
    ) {
        return service.getFloristPlants(floristId);
    }

    @Operation(summary = "Получение информации о флористе")
    @GetMapping(path = "/florist/{floristId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public FloristDTO getFlorist(@PathVariable Long floristId){
        return service.getFlorist(floristId);
    }
}
