package ru.plants.care.back.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import ru.plants.care.back.dto.image.ImageDTO;
import ru.plants.care.back.repository.model.ImageEntity;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        componentModel = "spring")
public interface ImageMapper {
    ImageDTO ImageEntityToImageDTO(ImageEntity imageEntity);
    ImageEntity ImageDTOToImageEntity(ImageDTO imageDTO);
}
