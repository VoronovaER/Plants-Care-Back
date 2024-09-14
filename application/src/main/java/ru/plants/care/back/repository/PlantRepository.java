package ru.plants.care.back.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.plants.care.back.repository.model.PlantEntity;

public interface PlantRepository extends JpaRepository<PlantEntity, Long>  {
}
