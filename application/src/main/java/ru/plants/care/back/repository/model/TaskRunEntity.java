package ru.plants.care.back.repository.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import ru.plants.care.back.dto.task.TaskRunStatus;

import java.time.LocalDateTime;
import java.util.List;

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
    @JoinColumn(name = "task_id")
    private TaskEntity task;

    @OneToMany(mappedBy = "taskRun", fetch = FetchType.EAGER)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<NotificationEntity> notificationEntities;

    @Builder.Default
    private LocalDateTime startAt= LocalDateTime.now();
    private LocalDateTime endAt;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    private TaskRunStatus status = TaskRunStatus.WAITING;

    private String statusDescription;
}
