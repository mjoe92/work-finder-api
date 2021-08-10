package com.codecool.workfinder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class WorkFinderApplication {

    private static final Logger logger = LoggerFactory
            .getLogger(WorkFinderApplication.class);

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    public static void main(String[] args) {
        SpringApplication.run(WorkFinderApplication.class, args);
        logging(8081);
    }

    private static void logging(int port) {
        logger.info("--------------------");
        logger.info(String.format("Application is running in: http://localhost:%d", port));
        logger.info(String.format("Swagger documentation: http://localhost:%d/swagger", port));
        logger.info("--------------------");
    }
}