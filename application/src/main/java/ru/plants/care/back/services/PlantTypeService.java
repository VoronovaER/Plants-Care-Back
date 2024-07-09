package ru.plants.care.back.services;

import ru.plants.care.back.dto.PlantTypeDTO;
import ru.plants.care.back.dto.PlantTypeListRecordDTO;

import java.util.List;

public interface PlantTypeService {
    List<PlantTypeListRecordDTO> getPlantTypeList();
    PlantTypeDTO savePlantType(PlantTypeDTO plantType);
    void deletePlantType(Long id);
}
