package ru.plants.care.back.services.impl;

import jakarta.transaction.Transactional;
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
import ru.plants.care.back.repository.model.FloristEntity;
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
    @Transactional
    public PlantDTO createPlant(@Validated NewPlantRequestDTO plant) {
        var floristEntityOpt = floristRepository.findById(plant.getFloristId());
        if (floristEntityOpt.isEmpty()) {
            throw new ItemNotFoundException("Florist not found: " + plant.getFloristId());
        }
        var florist = floristEntityOpt.get();

        var plantTypeEntity = plantTypeRepository.findById(plant.getPlantTypeId());
        if (plantTypeEntity.isEmpty()) {
            throw new ItemNotFoundException("Plant type not found: " + plant.getPlantTypeId());
        }

        var plantEntity = mapper.newPlantDTOToListPlantEntity(plant);
        plantEntity.setPlantType(plantTypeEntity.get());
        plantEntity = repository.saveAndFlush(plantEntity);

        florist.getPlants().add(plantEntity);

        if (plantEntity.getFlorists() == null) {
            plantEntity.setFlorists(List.of(florist));
        } else {
            plantEntity.getFlorists().add(florist);
        }

        return mapper.plantEntityToPlantDTO(plantEntity);
    }

    @Override
    public PlantDTO updatePlant(Long id, BasePlantDTO plant) {
        var plantEntity = repository.findById(id);
        if (plantEntity.isEmpty()) {
            throw new ItemNotFoundException("Plant not found: " + id);
        }
        plantEntity.get().setFlorists(plantEntity.get().getFlorists());

        mapper.updatePlantEntity(plant, plantEntity.get());
        return mapper.plantEntityToPlantDTO(plantRepository.save(plantEntity.get()));
    }

    @Override
    public PlantDTO getPlant(Long id) {
        var plantEntity = repository.findById(id);
        if (plantEntity.isEmpty()) {
            throw new ItemNotFoundException("Plant not found: " + id);
        }

        return mapper.plantEntityToPlantDTO(plantEntity.get());
    }

    @Override
    public void deletePlant(Long id) {
        var plantEntity = repository.findById(id);
        if (plantEntity.isEmpty()) {
            throw new ItemNotFoundException("Plant not found: " + id);
        }else{
            List<FloristEntity> florists = plantEntity.get().getFlorists();
            for (FloristEntity florist : florists) {
                florist.getPlants().remove(plantEntity.get());
            }
            repository.deleteById(id);
        }
    }
}
