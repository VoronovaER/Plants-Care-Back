package ru.plants.care.back.services;

import org.springframework.beans.factory.xml.ResourceEntityResolver;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.ResponseEntity;
import ru.plants.care.back.dto.image.ImageDTO;

import java.io.File;
import java.io.IOException;

public interface ImageService {
    ResponseEntity<String> uploadImage(MultipartFile file, Long floristId) throws IOException;
    ResponseEntity<byte[]> getImage(String fileId) throws IOException;
    String deleteImage(String fileId) throws IOException;
}
