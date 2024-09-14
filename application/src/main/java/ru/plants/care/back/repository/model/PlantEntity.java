package ru.plants.care.back.repository.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.LinkedList;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "plants")
public class PlantEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private PlantTypeEntity plantType;

    private String name;

    private String place;

    @ManyToMany(mappedBy = "plants")
    private List<FloristEntity> florists = new LinkedList<>();
}