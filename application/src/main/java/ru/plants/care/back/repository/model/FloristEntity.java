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
@Table(name = "florist", indexes = {
        @Index(name = "florist_idx1", columnList = "email", unique = true)
})
public class FloristEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String avatar;

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinTable(
            name = "florist_plants",
            joinColumns = { @JoinColumn(name = "florist_id") },
            inverseJoinColumns = { @JoinColumn(name = "plant_id") }
    )
    private List<PlantEntity> plants = new LinkedList<>();

    @Column(unique = true)
    private String email;
    private String password;
    private String firebaseToken;
    private Boolean Admin;
}