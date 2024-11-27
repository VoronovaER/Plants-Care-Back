package ru.plants.care.back.services;

import org.springframework.web.multipart.MultipartFile;
import ru.plants.care.back.dto.image.ImageDTO;

import java.io.File;
import java.io.IOException;

public interface ImageService {
    ImageDTO uploadImage(MultipartFile file, Long floristId) throws IOException;
    byte[] getImage(String url) throws IOException;
    String deleteImage(String url) throws IOException;
}
