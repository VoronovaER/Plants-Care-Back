package ru.plants.care.back.exception;

import org.springframework.http.HttpStatus;

public class DuplicateKeyException extends PlantBaseException {
    public DuplicateKeyException(String message) {
        super(HttpStatus.BAD_REQUEST, message);
    }
}
