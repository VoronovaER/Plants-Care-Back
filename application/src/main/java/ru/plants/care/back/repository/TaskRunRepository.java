package ru.plants.care.back.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.plants.care.back.dto.task.TaskRunStatus;
import ru.plants.care.back.repository.model.TaskEntity;
import ru.plants.care.back.repository.model.TaskRunEntity;

import java.time.LocalDateTime;
import java.util.List;

public interface TaskRunRepository extends JpaRepository<TaskRunEntity, Long> {
    List<TaskRunEntity> findAllByStatus(TaskRunStatus status);
    List<TaskRunEntity> findAllByTaskAndStartAtBetween(TaskEntity task,
                                                       LocalDateTime begin,
                                                       LocalDateTime end);
}
