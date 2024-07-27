package ru.plants.care.back.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.plants.care.back.dto.florist.BaseFloristDTO;
import ru.plants.care.back.dto.florist.FloristDTO;
import ru.plants.care.back.exception.DuplicateKeyException;
import ru.plants.care.back.mapper.FloristMapper;
import ru.plants.care.back.repository.FloristRepository;
import ru.plants.care.back.services.FloristService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FloristServiceImpl implements FloristService {
    private final FloristRepository floristRepository;
    private final FloristMapper mapper;

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
}
