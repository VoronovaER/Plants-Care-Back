package ru.plants.care.back.exception;

import org.springframework.http.HttpStatus;

public class ItemNotFoundException extends PlantBaseException {

    public ItemNotFoundException(String message) {
        super(HttpStatus.NOT_FOUND, message);
    }
}
