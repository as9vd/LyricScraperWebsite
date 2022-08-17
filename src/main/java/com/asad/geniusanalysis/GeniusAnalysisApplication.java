package com.asad.geniusanalysis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("com.asad.geniusanalysis")
public class GeniusAnalysisApplication {
    // Immediate issues to fix:
    // 1. CSS styling with button being clicked.
    // 2. Ugly FAQ page.
    //
    // Things to look at in future:
    // 1. CSRF tokens.
    // 2. Updating code involving directories as may not be consistent on AWS.
    public static void main(String[] args) {
        SpringApplication.run(GeniusAnalysisApplication.class, args);
    }
}
