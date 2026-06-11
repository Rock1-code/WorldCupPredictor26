package com.rocky.WorldCupPredictor26;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class WorldCupPredictor26Application {

    public static void main(String[] args) {
        SpringApplication.run(WorldCupPredictor26Application.class, args);
    }
}