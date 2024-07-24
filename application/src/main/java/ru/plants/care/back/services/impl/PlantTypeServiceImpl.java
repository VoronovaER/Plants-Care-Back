package ru.plants.care.back.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.plants.care.back.dto.planttype.PlantTypeDTO;
import ru.plants.care.back.dto.planttype.PlantTypeListRecordDTO;
import ru.plants.care.back.mapper.PlantTypeMapper;
import ru.plants.care.back.repository.PlantTypeRepository;
import ru.plants.care.back.services.PlantTypeService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlantTypeServiceImpl implements PlantTypeService {
    private final PlantTypeRepository plantTypeRepository;
    private final PlantTypeMapper plantTypeMapper;

    @Override
    public List<PlantTypeListRecordDTO> getPlantTypeList() {
        return plantTypeMapper.plantTypeEntityToPlantTypeListRecordDTO(plantTypeRepository.findAll());
    }

    @Override
    public PlantTypeDTO savePlantType(PlantTypeDTO plantType) {
        return plantTypeMapper.plantTypeEntityToPlantTypeDTO(plantTypeRepository.save(plantTypeMapper.PlantTypeDTOToPlantTypeEntity(plantType)));

    }

    @Override
    public void deletePlantType(Long id) {
        plantTypeRepository.deleteById(id);
    }

}
