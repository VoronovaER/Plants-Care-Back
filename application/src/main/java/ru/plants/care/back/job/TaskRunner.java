package ru.plants.care.back.job;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.plants.care.back.dto.task.TaskRunStatus;
import ru.plants.care.back.repository.TaskRepository;
import ru.plants.care.back.repository.TaskRunRepository;
import ru.plants.care.back.repository.model.TaskEntity;
import ru.plants.care.back.repository.model.TaskRunEntity;
import ru.plants.care.back.task.FeedingTask;
import ru.plants.care.back.task.PollingTask;
import ru.plants.care.back.task.Task;
import ru.plants.care.back.task.WateringTask;

import java.time.LocalDateTime;
import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class TaskRunner {
    private final ApplicationContext context;
    private final TaskRepository taskRepository;
    private final TaskRunRepository taskRunRepository;

    @Scheduled(fixedRate = 1000)
    public void planTasksRun() {
        var taskList = taskRepository.getListOfNotCompletedTasks();
        for (var task : taskList) {
            log.debug("Будет запущена задача {}", task.getName());

            taskRunRepository.save(TaskRunEntity.builder()
                    .task(task)
                    .status(TaskRunStatus.WAITING)
                    .build());

            switch (task.getPeriod()) {
                case HOURLY:
                    task.setNextRun(LocalDateTime.now().plusHours(1));
                    break;
                case DAILY:
                    task.setNextRun(LocalDateTime.now().plusDays(1));
                    break;
                case WEEKLY:
                    task.setNextRun(LocalDateTime.now().plusWeeks(1));
                    break;
                case MONTHLY:
                    task.setNextRun(LocalDateTime.now().plusMonths(1));
                    break;
                case YEARLY:
                    task.setNextRun(LocalDateTime.now().plusYears(1));
                    break;
                default:
            }

            taskRepository.save(task);
        }
    }

    @Scheduled(fixedRate = 1000)
    public void executeTasks() {
        var taskRunList = taskRunRepository.findAllByStatus(TaskRunStatus.WAITING);
        for (var taskRun : taskRunList) {
            setTaskRunStatus(taskRun, TaskRunStatus.RUNNING);
            CompletableFuture.runAsync(() -> getTask(taskRun.getTask()).execute(taskRun))
                    .whenComplete((result, ex) -> {
                        if (ex != null) {
                            taskRun.setStatusDescription(ex.getClass().getSimpleName() + ": " + ex.getMessage());
                            setTaskRunStatus(taskRun, TaskRunStatus.FAILED);
                        } else {
                            setTaskRunStatus(taskRun, TaskRunStatus.SUCCESS);
                        }
                    });
        }
    }

    public void setTaskRunStatus(TaskRunEntity taskRun, TaskRunStatus status) {
        taskRun.setEndAt(LocalDateTime.now());
        taskRun.setStatus(status);
        taskRunRepository.save(taskRun);
    }

    private Task getTask(TaskEntity task) {
        return switch (task.getType()) {
            case PLANT_WATERING -> context.getBean(WateringTask.class);
            case PLANT_FEEDING -> context.getBean(FeedingTask.class);
            case PLANT_POLLING -> context.getBean(PollingTask.class);
        };
    }
}
