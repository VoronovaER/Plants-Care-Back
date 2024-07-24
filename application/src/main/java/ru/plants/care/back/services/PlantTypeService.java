package ru.plants.care.back.services;

import ru.plants.care.back.dto.planttype.PlantTypeDTO;
import ru.plants.care.back.dto.planttype.PlantTypeListRecordDTO;

import java.util.List;

public interface PlantTypeService {
    List<PlantTypeListRecordDTO> getPlantTypeList();
    PlantTypeDTO savePlantType(PlantTypeDTO plantType);
    void deletePlantType(Long id);
}
