package ru.plants.care.back.exception.handler;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ru.plants.care.back.dto.ErrorDTO;
import ru.plants.care.back.exception.PlantBaseException;

@ControllerAdvice
public class PlantsCareBackExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = {PlantBaseException.class})
    protected ResponseEntity<Object> handleItemNotFoundException(PlantBaseException ex, WebRequest request) {
        return handleExceptionInternal(ex, ErrorDTO.builder()
                        .type(ex.getClass().getSimpleName())
                        .detail(ex.getMessage())
                        .build(),
                new HttpHeaders(), ex.getStatus(), request);
    }
}
