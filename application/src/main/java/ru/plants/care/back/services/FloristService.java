package ru.plants.care.back.services;

import ru.plants.care.back.dto.florist.BaseFloristDTO;
import ru.plants.care.back.dto.florist.FloristDTO;

import java.util.List;

public interface FloristService {
    BaseFloristDTO saveFlorist(BaseFloristDTO florist);
    List<FloristDTO> getFloristList();
    void deleteFlorist(Long id);
}