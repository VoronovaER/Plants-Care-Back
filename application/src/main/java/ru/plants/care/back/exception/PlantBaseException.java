package ru.plants.care.back.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public abstract class PlantBaseException extends RuntimeException {
    private final HttpStatus status;

    public PlantBaseException(HttpStatus status, String message) {
        super(message);
        this.status = status;
    }
}
