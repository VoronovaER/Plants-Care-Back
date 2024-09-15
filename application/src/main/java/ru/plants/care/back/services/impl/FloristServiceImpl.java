package ru.plants.care.back.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.plants.care.back.dto.florist.BaseFloristDTO;
import ru.plants.care.back.dto.florist.FloristDTO;
import ru.plants.care.back.dto.plant.PlantListRecordDTO;
import ru.plants.care.back.exception.DuplicateKeyException;
import ru.plants.care.back.exception.ItemNotFoundException;
import ru.plants.care.back.mapper.FloristMapper;
import ru.plants.care.back.mapper.PlantMapper;
import ru.plants.care.back.repository.FloristRepository;
import ru.plants.care.back.repository.PlantRepository;
import ru.plants.care.back.services.FloristService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FloristServiceImpl implements FloristService {
    private final FloristRepository floristRepository;
    private final PlantRepository plantRepository;
    private final FloristMapper mapper;
    private final PlantMapper plantMapper;

    @Override
    public BaseFloristDTO saveFlorist(BaseFloristDTO florist) {
        var floristEntity = floristRepository.findByEmail(florist.getEmail());
        if (floristEntity != null) {
            throw new DuplicateKeyException("Уже существует запись с таким email:  " + florist.getEmail());
        }

        return mapper.floristEntityToFloristDto(floristRepository.save(mapper.baseFloristDtoToFloristEntity(florist)));
    }

    @Override
    public void deleteFlorist(Long id) {
        floristRepository.deleteById(id);
    }

    @Override
    public List<FloristDTO> getFloristList() {
        return mapper.floristEntityToFloristDto(floristRepository.findAll());
    }

    @Override
    public void addPlant(Long floristId, Long plantId) {
        var floristEntity = floristRepository.findById(floristId);
        if (floristEntity.isEmpty()) {
            throw new ItemNotFoundException("Florist not found: " + floristId);
        }

        var plantEntity = plantRepository.findById(plantId);
        if (plantEntity.isEmpty()) {
            throw new ItemNotFoundException("Plant not found: " + plantId);
        }
        if(floristEntity.get().getPlants().contains(plantEntity.get())){
            throw new DuplicateKeyException("Florist already has this plant: " + plantId);
        }

        floristEntity.get().getPlants().add(plantEntity.get());
        floristRepository.save(floristEntity.get());
    }

    @Override
    public BaseFloristDTO updateFlorist(Long id, BaseFloristDTO floristDTO) {
        var floristEntity = floristRepository.findById(id);
        if (floristEntity.isEmpty()) {
            throw new ItemNotFoundException("Florist not found: " + id);
        }
        floristEntity.get().setPlants(floristEntity.get().getPlants());

        mapper.updateFloristEntity(floristDTO, floristEntity.get());
        return mapper.floristEntityToFloristDto(floristRepository.save(floristEntity.get()));
    }

    @Override
    public List<PlantListRecordDTO> getFloristPlants(Long id) {
        var floristEntity = floristRepository.findById(id);
        if (floristEntity.isEmpty()) {
            throw new ItemNotFoundException("Florist not found: " + id);
        }
        return plantMapper.plantEntityToListPlantDTO(floristEntity.get().getPlants());
    }
}
