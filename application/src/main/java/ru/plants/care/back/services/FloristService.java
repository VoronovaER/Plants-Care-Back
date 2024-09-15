package ru.plants.care.back.services;

import ru.plants.care.back.dto.florist.BaseFloristDTO;
import ru.plants.care.back.dto.florist.FloristDTO;
import ru.plants.care.back.dto.plant.PlantListRecordDTO;

import java.util.List;

public interface FloristService {
    BaseFloristDTO saveFlorist(BaseFloristDTO florist);
    List<FloristDTO> getFloristList();
    void deleteFlorist(Long id);
    void addPlant(Long floristId, Long plantId);
    BaseFloristDTO updateFlorist(Long id, BaseFloristDTO floristDTO);
    List<PlantListRecordDTO> getFloristPlants(Long id);
}