package ru.plants.care.back.mapper;

import org.mapstruct.*;
import ru.plants.care.back.dto.florist.BaseFloristDTO;
import ru.plants.care.back.dto.florist.FloristDTO;
import ru.plants.care.back.repository.model.FloristEntity;

import java.util.List;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        componentModel = "spring")
public interface FloristMapper {
    FloristDTO floristEntityToFloristDto(FloristEntity value);

    List<FloristDTO> floristEntityToFloristDto(List<FloristEntity> floristList);

    FloristEntity baseFloristDtoToFloristEntity(BaseFloristDTO florist);

    void updateFloristEntity(BaseFloristDTO floristDTO, @MappingTarget FloristEntity floristEntity);
}
