package ru.plants.care.back.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import ru.plants.care.back.config.properties.AppProperties;

@Configuration
@EnableJpaRepositories(basePackages = "ru.plants.care.back.repository")
@EnableScheduling
@EnableConfigurationProperties(AppProperties.class)
public class AppConfig {

}