package ru.plants.care.back.services;

import ru.plants.care.back.dto.plant.BasePlantDTO;
import ru.plants.care.back.dto.plant.NewPlantRequestDTO;
import ru.plants.care.back.dto.plant.PlantDTO;
import ru.plants.care.back.dto.plant.PlantListRecordDTO;
import ru.plants.care.back.dto.task.TaskListRecordDTO;

import java.util.List;

public interface PlantService {
    List<PlantListRecordDTO> getPlantList();
    PlantDTO createPlant(NewPlantRequestDTO plant);
    PlantDTO updatePlant(Long id, BasePlantDTO plant);
    PlantDTO getPlant(Long id);
    void deletePlant(Long id);
    List<TaskListRecordDTO> getTaskList(Long id);
}
