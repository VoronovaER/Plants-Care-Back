package ru.plants.care.back.repository.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    @ManyToOne(fetch = FetchType.EAGER)
    private PlantTypeEntity plantType;

    private String name;
    private String place;
    private String imageUrl;

    @ManyToMany(mappedBy = "plants", fetch = FetchType.EAGER)
    private List<FloristEntity> florists = new LinkedList<>();
    @OneToMany(mappedBy = "plant", fetch = FetchType.EAGER)
    private List<TaskEntity> tasks = new LinkedList<>();
}