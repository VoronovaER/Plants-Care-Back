package ru.plants.care.back.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.plants.care.back.dto.task.NewTaskDTO;
import ru.plants.care.back.dto.task.TaskDTO;
import ru.plants.care.back.dto.task.TaskListRecordDTO;
import ru.plants.care.back.services.TaskService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/api/v1/task")
@Schema(name = "Задачи", description = "Работа с данными задач")
public class TaskController {
    private final TaskService taskService;

    @Operation(summary = "Добавление задачи")
    @PostMapping(path = "/add/{floristId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public TaskDTO createTask(
            @RequestBody NewTaskDTO taskDTO,
            @PathVariable Long floristId
    ) {
        return taskService.createTask(floristId, taskDTO);
    }

    @Operation(summary  =  "Список задач")
    @GetMapping(path  =  "/list", produces  = MediaType.APPLICATION_JSON_VALUE)
    public List<TaskListRecordDTO> getTaskList(){
        return taskService.getTaskList();
    }

    @Operation(summary  =  "Получение информации о задаче")
    @GetMapping(path   =   "/{taskId}", produces   = MediaType.APPLICATION_JSON_VALUE)
    public TaskDTO getTask(@PathVariable Long taskId){
        return taskService.getTask(taskId);
    }

    @Operation(summary = "Изменение статуса задачи")
    @PutMapping(path  =  "/{taskId}", produces  = MediaType.APPLICATION_JSON_VALUE)
    public TaskDTO changeTaskStatus(@PathVariable Long taskId){
        return taskService.changeTaskStatus(taskId);
    }

    @Operation(summary = "Удаление задачи")
    @DeleteMapping(path  =  "/{taskId}")
    public void deleteTask(@PathVariable Long taskId){
        taskService.deleteTask(taskId);
    }
}
