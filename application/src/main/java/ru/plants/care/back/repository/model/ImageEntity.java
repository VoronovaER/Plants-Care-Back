package ru.plants.care.back.repository.model;

import jakarta.persistence.*;
import jdk.jfr.ContentType;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "images")
public class ImageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fileId;

    private String originalFileName;

    @ManyToOne(fetch = FetchType.LAZY)
    private FloristEntity florist;
    private String contentType;

}
