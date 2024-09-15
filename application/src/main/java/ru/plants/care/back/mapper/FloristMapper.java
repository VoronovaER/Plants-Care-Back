package ru.plants.care.back.mapper;

import org.mapstruct.*;
import ru.plants.care.back.dto.florist.BaseFloristDTO;
import ru.plants.care.back.dto.florist.FloristDTO;
import ru.plants.care.back.repository.model.FloristEntity;

import java.util.List;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        componentModel = "spring")
public interface FloristMapper {
    @Mapping(target = "plantsQuantity", expression = "java(value.getPlants() != null ? value.getPlants().size() : 0)")
    FloristDTO floristEntityToFloristDto(FloristEntity value);

    List<FloristDTO> floristEntityToFloristDto(List<FloristEntity> floristList);

    FloristEntity baseFloristDtoToFloristEntity(BaseFloristDTO florist);

    FloristEntity floristDtoToFloristEntity(FloristDTO value);

    void updateFloristEntity(BaseFloristDTO floristDTO, @MappingTarget FloristEntity floristEntity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    FloristEntity partialUpdate(FloristDTO floristDTO, @MappingTarget FloristEntity floristEntity);
}
