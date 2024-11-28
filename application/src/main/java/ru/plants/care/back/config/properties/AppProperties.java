package ru.plants.care.back.config.properties;


import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "plants-care")
@Setter
@Getter
public class AppProperties {
    private String fileStoragePath;
}
