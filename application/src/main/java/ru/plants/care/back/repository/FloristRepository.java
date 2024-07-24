package ru.plants.care.back.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.plants.care.back.repository.model.FloristEntity;

public interface FloristRepository extends JpaRepository<FloristEntity, Long>{
    FloristEntity findByEmail(String email);
}

