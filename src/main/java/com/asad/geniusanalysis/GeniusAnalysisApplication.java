package com.asad.geniusanalysis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("com.asad.geniusanalysis")
public class GeniusAnalysisApplication {
    public static void main(String[] args) {
        SpringApplication.run(GeniusAnalysisApplication.class, args);
    }
}