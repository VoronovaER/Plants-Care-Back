package ru.plants.care.back.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.plants.care.back.repository.model.PlantTypeEntity;

public interface PlantTypeRepository extends JpaRepository<PlantTypeEntity, Long> {
}
