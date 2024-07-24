package ru.plants.care.back.repository.model;

import jakarta.persistence.*;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "florist", indexes = {
        @Index(name = "florist_idx1", columnList = "email")
})
public class FloristEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String avatar;

    @Column(unique = true)
    private String email;
}