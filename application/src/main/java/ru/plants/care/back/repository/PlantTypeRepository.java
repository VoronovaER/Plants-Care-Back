package ru.plants.care.back.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.plants.care.back.repository.model.PlantTypeEntity;

import java.util.List;

public interface PlantTypeRepository extends JpaRepository<PlantTypeEntity, Long> {
    List<PlantTypeEntity> findByNameContainingIgnoreCase(String name);
}
