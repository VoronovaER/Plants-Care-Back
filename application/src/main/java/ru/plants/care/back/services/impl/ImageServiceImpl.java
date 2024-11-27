package ru.plants.care.back.services.impl;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.plants.care.back.dto.florist.FloristDTO;
import ru.plants.care.back.dto.image.ImageDTO;
import ru.plants.care.back.mapper.ImageMapper;
import ru.plants.care.back.repository.FloristRepository;
import ru.plants.care.back.repository.ImageRepository;
import ru.plants.care.back.repository.PlantRepository;
import ru.plants.care.back.repository.model.FloristEntity;
import ru.plants.care.back.repository.model.ImageEntity;
import ru.plants.care.back.repository.model.PlantEntity;
import ru.plants.care.back.services.ImageService;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {
    private final FloristRepository floristRepository;
    private final ImageRepository imageRepository;
    private final ImageMapper imageMapper;
    @Override
    public ImageDTO uploadImage(MultipartFile img, Long floristId) throws IOException {
        String uploadDirectory = "src/main/resources/static/images";
        String uniqueFileName = UUID.randomUUID() + "_" + img.getName();
        Path uploadPath = Path.of(uploadDirectory);
        Path filePath = uploadPath.resolve(uniqueFileName);

        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        Files.copy(img.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
        ImageEntity image = new ImageEntity();
        image.setUrl(uploadDirectory+"/"+uniqueFileName);
        FloristEntity florist = floristRepository.findById(floristId).get();
        image.setFlorist(florist);
        imageRepository.save(image);
        return imageMapper.ImageEntityToImageDTO(image);
    }

    @Override
    public byte[] getImage(String url) throws IOException {
        ImageEntity image = imageRepository.findByUrl(url);
        Path imagePath = Path.of(image.getUrl());
        if(Files.exists(imagePath)) {
            return Files.readAllBytes(imagePath);
        }return null;
    }

    @Override
    public String deleteImage(String url) throws IOException {
        Path imagePath = Path.of(url);

        if (Files.exists(imagePath)) {
            Files.delete(imagePath);
            return "Success";
        } else {
            return "Failed";
        }
    }
}
