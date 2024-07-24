package ru.plants.care.back.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import ru.plants.care.back.dto.florist.BaseFloristDTO;
import ru.plants.care.back.dto.florist.FloristDTO;
import ru.plants.care.back.mapper.FloristMapper;
import ru.plants.care.back.repository.FloristRepository;
import ru.plants.care.back.services.FloristService;

import java.util.List;
import java.util.NoSuchElementException;

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

    @Override
    public BaseFloristDTO updateFlorist(Long id, BaseFloristDTO floristDTO) {
        var floristEntity = floristRepository.findById(id);
        if (floristEntity.isEmpty()) {
            throw new NoSuchElementException("Florist not found: " + id);
        }
        mapper.updateFloristEntity(floristDTO, floristEntity.get());
        return mapper.floristEntityToFloristDto(floristRepository.save(floristEntity.get()));
    }
}
