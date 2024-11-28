package ru.plants.care.back.services.impl;


import jdk.jfr.ContentType;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.plants.care.back.config.properties.AppProperties;
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
import java.util.Base64;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {
    private final FloristRepository floristRepository;
    private final ImageRepository imageRepository;
    private final ImageMapper imageMapper;
    private final AppProperties appProperties;
    @Override
    public ResponseEntity<String> uploadImage(MultipartFile img, Long floristId) throws IOException {
        String uploadDirectory = appProperties.getFileStoragePath();//TODO: добавить папку флориста
        String uniqueFileName = UUID.randomUUID().toString();
        Path uploadPath = Path.of(uploadDirectory);
        Path filePath = uploadPath.resolve(uniqueFileName);

        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        Files.copy(img.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
        ImageEntity image = new ImageEntity();
        image.setFileId(uniqueFileName);
        image.setOriginalFileName(img.getOriginalFilename());
        image.setContentType(img.getContentType());
        FloristEntity florist = floristRepository.findById(floristId).get();
        image.setFlorist(florist);
        imageRepository.save(image);
        return ResponseEntity.ok().body(image.getFileId());
    }

    @Override
    public ResponseEntity<byte[]> getImage(String fileId) throws IOException {
        ImageEntity image = imageRepository.findByFileId(fileId);
        String uploadDirectory = appProperties.getFileStoragePath();//TODO: добавить папку флориста
        Path imagePath = Path.of(uploadDirectory + "/" + image.getFileId());
        byte[] fileContent = Files.readAllBytes(imagePath);
        if(Files.exists(imagePath)) {
            return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, image.getContentType()).body(Files.readAllBytes(imagePath));
        }
        return ResponseEntity.notFound().build();
    }

    @Override
    public String deleteImage(String fileId) throws IOException {
        ImageEntity image = imageRepository.findByFileId(fileId);
        String uploadDirectory = appProperties.getFileStoragePath();//TODO: добавить папку флориста
        Path imagePath = Path.of(uploadDirectory + "/" + image.getFileId());
        for (PlantEntity plant : image.getFlorist().getPlants()) {
            if(plant.getUrl().equals(image.getFileId())){
                plant.setUrl(null);
            }
        }
        imageRepository.deleteById(image.getId());

        if (Files.exists(imagePath)) {
            Files.delete(imagePath);
            return "Success";
        } else {
            return "Failed";
        }
    }
}
