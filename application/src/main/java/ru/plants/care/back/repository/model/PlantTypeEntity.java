package ru.plants.care.back.repository.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "plant_type")
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
public class PlantTypeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String url;
    private String latinName;
    private String description;
    private String blossom;
    private String sumWatering;
    private String winWatering;
    private String light;
    private int sumTempMin;
    private int sumTempMax;
    private int winTempMin;
    private int winTempMax;
    private String types;
    private String fertilize;
    private String transplant;
}
