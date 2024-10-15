package ru.plants.care.back.repository.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "notifications")
public class NotificationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime createdAt = LocalDateTime.now();

    @ManyToOne(fetch = FetchType.LAZY)
    private FloristEntity florist;

    @ManyToOne(fetch = FetchType.LAZY)
    private TaskRunEntity taskRun;

    private LocalDateTime delivered;
    private LocalDateTime read;
}