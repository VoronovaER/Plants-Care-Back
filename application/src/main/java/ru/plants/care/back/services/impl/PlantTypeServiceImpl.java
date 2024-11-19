package ru.plants.care.back.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.plants.care.back.dto.planttype.PlantTypeDTO;
import ru.plants.care.back.dto.planttype.PlantTypeListRecordDTO;
import ru.plants.care.back.exception.ItemNotFoundException;
import ru.plants.care.back.mapper.PlantTypeMapper;
import ru.plants.care.back.repository.PlantTypeRepository;
import ru.plants.care.back.repository.model.PlantTypeEntity;
import ru.plants.care.back.services.PlantTypeService;

import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PlantTypeServiceImpl implements PlantTypeService {
    private final PlantTypeRepository plantTypeRepository;
    private final PlantTypeMapper plantTypeMapper;

    @Override
    public List<PlantTypeListRecordDTO> getPlantTypeList() {
        List<PlantTypeEntity> plantTypeEntities = plantTypeRepository.findAll();
        plantTypeEntities.sort(Comparator.comparing(PlantTypeEntity::getName));
        return plantTypeMapper.plantTypeEntityToPlantTypeListRecordDTO(plantTypeEntities);
    }

    @Override
    public List<PlantTypeListRecordDTO> getPlantTypeListByName(String name) {
        return plantTypeMapper.plantTypeEntityToPlantTypeListRecordDTO(plantTypeRepository.findByNameContainingIgnoreCase(name));
    }

    @Override
    public PlantTypeDTO savePlantType(PlantTypeDTO plantType) {
        return plantTypeMapper.plantTypeEntityToPlantTypeDTO(plantTypeRepository.save(plantTypeMapper.PlantTypeDTOToPlantTypeEntity(plantType)));

    }

    @Override
    public void deletePlantType(Long id) {
        plantTypeRepository.deleteById(id);
    }

    @Override
    public PlantTypeDTO updatePlantType(Long id, PlantTypeDTO plant) {
        var plantTypeEntity = plantTypeRepository.findById(id);
        if (plantTypeEntity.isEmpty()) {
            throw new ItemNotFoundException("Plant not found: " + id);
        }
        plantTypeMapper.updatePlantTypeEntity(plant, plantTypeEntity.get());
        return plantTypeMapper.plantTypeEntityToPlantTypeDTO(plantTypeRepository.save(plantTypeEntity.get()));
    }

    @Override
    public PlantTypeDTO getPlantType(Long id) {
        var plantTypeEntity = plantTypeRepository.findById(id);
        if (plantTypeEntity.isEmpty()) {
            throw new ItemNotFoundException("Plant not found: " + id);
        }
        return plantTypeMapper.plantTypeEntityToPlantTypeDTO(plantTypeEntity.get());
    }
}
