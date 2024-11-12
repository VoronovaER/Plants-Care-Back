package ru.plants.care.back.services;

import ru.plants.care.back.dto.plant.BasePlantDTO;
import ru.plants.care.back.dto.plant.PlantDTO;
import ru.plants.care.back.dto.planttype.BasePlantTypeDTO;
import ru.plants.care.back.dto.planttype.PlantTypeDTO;
import ru.plants.care.back.dto.planttype.PlantTypeListRecordDTO;

import java.util.List;

public interface PlantTypeService {
    List<PlantTypeListRecordDTO> getPlantTypeList();

    List<PlantTypeListRecordDTO> getPlantTypeListByName(String name);

    PlantTypeDTO savePlantType(PlantTypeDTO plantType);
    void deletePlantType(Long id);
    PlantTypeDTO updatePlantType(Long id, PlantTypeDTO plant);
    PlantTypeDTO getPlantType(Long id);
}
