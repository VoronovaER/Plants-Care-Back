package ru.plants.care.back.services;
import ru.plants.care.back.dto.florist.BaseFloristDTO;
import ru.plants.care.back.dto.florist.FloristDTO;
import ru.plants.care.back.dto.florist.FloristTaskDTO;
import ru.plants.care.back.dto.plant.PlantListRecordDTO;

import java.time.LocalDate;
import java.util.List;

public interface FloristService{
    FloristDTO saveFlorist(BaseFloristDTO florist);
    List<FloristDTO> getFloristList();
    void deleteFlorist(Long id);
    void addPlant(Long floristId, Long plantId);
    BaseFloristDTO updateFlorist(Long id, BaseFloristDTO floristDTO);
    List<PlantListRecordDTO> getFloristPlants(Long id);
    FloristDTO getFlorist(Long id);
    List<FloristTaskDTO> getFloristTasksAtDate(Long id, LocalDate date);
    List<FloristTaskDTO> getFloristTasks(Long id);
    FloristDTO getFloristByEmail(String email);
    void setFirebaseToken(String firebaseToken, String email);
}