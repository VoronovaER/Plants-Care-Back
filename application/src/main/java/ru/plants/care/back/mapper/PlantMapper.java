package ru.plants.care.back.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import ru.plants.care.back.dto.plant.BasePlantDTO;
import ru.plants.care.back.dto.plant.NewPlantRequestDTO;
import ru.plants.care.back.dto.plant.PlantDTO;
import ru.plants.care.back.dto.plant.PlantListRecordDTO;
import ru.plants.care.back.repository.model.PlantEntity;

import java.util.List;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        componentModel = "spring",
        uses = {FloristMapper.class, PlantTypeMapper.class}
)
public interface PlantMapper {
    List<PlantListRecordDTO> plantEntityToListPlantDTO(List<PlantEntity> plantList);

    @Mapping(source = "plantType.name", target = "plantType")
    PlantListRecordDTO plantEntityToPlantListRecordDTO(PlantEntity entity);

    PlantDTO plantEntityToPlantDTO(PlantEntity entity);

    PlantEntity newPlantDTOToListPlantEntity(NewPlantRequestDTO value);
}
