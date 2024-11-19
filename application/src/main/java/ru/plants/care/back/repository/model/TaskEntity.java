package ru.plants.care.back.repository.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import ru.plants.care.back.dto.task.TaskPeriod;
import ru.plants.care.back.dto.task.TaskType;

import java.time.LocalDateTime;
import java.util.List;

@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "tasks")
public class TaskEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime createdAt;

    @Enumerated(EnumType.STRING)
    private TaskType type;

    private String name;

    private String description;

    @ManyToOne(fetch = FetchType.EAGER)
    private PlantEntity plant;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    private LocalDateTime nextRun = startDate;

    @Enumerated(EnumType.STRING)
    private TaskPeriod period;

    private Boolean enabled;

    private Boolean sendNotification;

}
