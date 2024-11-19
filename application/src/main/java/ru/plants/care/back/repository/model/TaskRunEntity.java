package ru.plants.care.back.repository.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import ru.plants.care.back.dto.task.TaskRunStatus;

import java.time.LocalDateTime;

@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "tasks_run")
public class TaskRunEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private TaskEntity task;

    @Builder.Default
    private LocalDateTime startAt= LocalDateTime.now();
    private LocalDateTime endAt;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    private TaskRunStatus status = TaskRunStatus.WAITING;

    private String statusDescription;
}
