package ru.plants.care.back.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.plants.care.back.dto.image.ImageDTO;
import ru.plants.care.back.services.ImageService;

import java.io.File;
import java.io.IOException;


@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/api/v1")
@Schema(name = "Картинки", description = "Работа с данными картинками")
public class ImageController {
    private final ImageService imageService;

    @Operation(summary = "Загрузка картинки")
    @PostMapping(path = "/image/{floristId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ImageDTO uploadImage(@RequestParam("image") MultipartFile image,
                                @PathVariable Long floristId) throws IOException {
        return imageService.uploadImage(image, floristId);
    }

    @Operation(summary = "Просмотр картинки")
    @GetMapping(path = "/image", produces = MediaType.APPLICATION_JSON_VALUE)
    public byte[] getImage(@RequestParam String url) throws IOException {
        return imageService.getImage(url);
    }

    @Operation(summary = "Удаление картинки")
    @DeleteMapping(path = "/image")
    public String deleteImage(@RequestParam String url) throws IOException {
        return imageService.deleteImage(url);
    }
}
