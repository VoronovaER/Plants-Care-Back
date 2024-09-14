package ru.plants.care.back.services;

import ru.plants.care.back.dto.plant.BasePlantDTO;
import ru.plants.care.back.dto.plant.NewPlantRequestDTO;
import ru.plants.care.back.dto.plant.PlantDTO;
import ru.plants.care.back.dto.plant.PlantListRecordDTO;

import java.util.List;

public interface PlantService {
    List<PlantListRecordDTO> getPlantList();
    PlantDTO createPlant(NewPlantRequestDTO plant);
    void deletePlant(Long id);
}
