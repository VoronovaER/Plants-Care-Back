package ru.plants.care.back.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.plants.care.back.dto.task.TaskRunStatus;
import ru.plants.care.back.services.TaskRunService;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/api/v1/task")
@Schema(name = "Выполненные задачи", description = "Работа с данными выполненных задач")
public class TaskRunController {
    private final TaskRunService taskRunService;
    @Operation(summary = "Изменить статус задачи")
    @PutMapping("/run/{id}/{status}")
    public void changeStatus(@PathVariable Long id, @PathVariable TaskRunStatus status) {
        taskRunService.changeStatus(status, id);
    }
}
