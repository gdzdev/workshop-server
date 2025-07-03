package org.gdzdev.workshop.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import jakarta.annotation.PostConstruct;

import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;


@SpringBootApplication
@PropertySource("file:./.env")
public class WorkshopServerApplication {

    @Autowired
    private Environment env;

    public static void main(String[] args) {
        SpringApplication.run(WorkshopServerApplication.class, args);
    }

    @PostConstruct
    public void logActiveProfiles() {
        String[] profiles = env.getActiveProfiles();
        System.out.println("🚀 Active Spring Profiles: " + String.join(", ", profiles));
    }
}