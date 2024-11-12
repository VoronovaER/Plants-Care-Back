package ru.plants.care.back.config.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.io.Resource;

@ConfigurationProperties(prefix = "plants-care.firebase")
@Setter
@Getter
public class FirebaseProperties {
    private Resource serviceAccount;
}
