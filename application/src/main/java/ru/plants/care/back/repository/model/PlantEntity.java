package ru.plants.care.back.repository.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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
    private String description;

    @ManyToMany(mappedBy = "plants", fetch = FetchType.EAGER)
    private List<FloristEntity> florists = new LinkedList<>();
    @OneToMany(mappedBy = "plant", fetch = FetchType.EAGER)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<TaskEntity> tasks = new LinkedList<>();
}