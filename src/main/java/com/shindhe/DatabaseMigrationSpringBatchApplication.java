package com.shindhe;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableBatchProcessing
@ComponentScan({"com.shindhe.config", "com.shindhe.listener", "com.shindhe.processor"})
public class DatabaseMigrationSpringBatchApplication {

    public static void main(String[] args) {
        SpringApplication.run(DatabaseMigrationSpringBatchApplication.class, args);
    }

}
