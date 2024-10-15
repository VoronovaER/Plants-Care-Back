package ru.plants.care.back.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.plants.care.back.repository.model.TaskEntity;

import java.util.List;

public interface TaskRepository extends JpaRepository<TaskEntity, Long> {
    @Query("select t from TaskEntity t where t.enabled = true and (t.endDate is null or t.endDate >= current_date) " +
            " and t.nextRun <= current_timestamp" +
            " and not exists (select tr from TaskRunEntity tr where tr.task= t and tr.status in (ru.plants.care.back.dto.task.TaskRunStatus.WAITING, ru.plants.care.back.dto.task.TaskRunStatus.RUNNING))")
    List<TaskEntity> getListOfNotCompletedTasks();
}
