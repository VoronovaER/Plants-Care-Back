package ru.plants.care.back.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import ru.plants.care.back.dto.plant.BasePlantDTO;
import ru.plants.care.back.dto.plant.NewPlantRequestDTO;
import ru.plants.care.back.dto.plant.PlantDTO;
import ru.plants.care.back.dto.plant.PlantListRecordDTO;
import ru.plants.care.back.exception.ItemNotFoundException;
import ru.plants.care.back.mapper.PlantMapper;
import ru.plants.care.back.repository.FloristRepository;
import ru.plants.care.back.repository.PlantRepository;
import ru.plants.care.back.repository.PlantTypeRepository;
import ru.plants.care.back.services.PlantService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlantServiceImpl implements PlantService {
    private final PlantRepository repository;
    private final PlantMapper mapper;
    private final FloristRepository floristRepository;
    private final PlantTypeRepository plantTypeRepository;
    private final PlantRepository plantRepository;

    @Override
    public List<PlantListRecordDTO> getPlantList() {
        return mapper.plantEntityToListPlantDTO(repository.findAll());
    }

    @Override
    public PlantDTO createPlant(@Validated NewPlantRequestDTO plant) {
        var floristEntity = floristRepository.findById(plant.getFloristId());
        if (floristEntity.isEmpty()) {
            throw new ItemNotFoundException("Florist not found: " + plant.getFloristId());
        }

        var plantTypeEntity = plantTypeRepository.findById(plant.getPlantTypeId());
        if (plantTypeEntity.isEmpty()) {
            throw new ItemNotFoundException("Plant type not found: " + plant.getPlantTypeId());
        }

        var plantEntity = mapper.newPlantDTOToListPlantEntity(plant);
        plantEntity.setPlantType(plantTypeEntity.get());
        if (plantEntity.getFlorists() == null) {
            plantEntity.setFlorists(List.of(floristEntity.get()));
        } else {
            plantEntity.getFlorists().add(floristEntity.get());
        }

        return mapper.plantEntityToPlantDTO(plantRepository.save(plantEntity));
    }

    @Override
    public void deletePlant(Long id) {
        repository.deleteById(id);
    }
}
