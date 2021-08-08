package com.codecool.workfinder;

import io.swagger.v3.oas.models.servers.Server;
import org.apache.catalina.startup.Tomcat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springdoc.core.SpringDocUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.core.env.StandardEnvironment;
import org.springframework.web.client.RestTemplate;

import javax.websocket.server.ServerEndpoint;
import java.net.MalformedURLException;
import java.net.URL;

@SpringBootApplication
public class WorkFinderApplication {

    private static final Logger logger = LoggerFactory
            .getLogger(WorkFinderApplication.class);
/*
    private static Environment environment = new StandardEnvironment();

    @Value("${server.port}")
    private static int serverPort;

    private static ServerProperties serverProperties;
*/

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    public static void main(String[] args) {
        SpringApplication.run(WorkFinderApplication.class, args);
        //Environment envi = new StandardEnvironment();
        //ServerProperties properties = new ServerProperties();

        //System.out.println(envi.getProperty("server.port"));

        logger.info("Swagger documentation: ");
        logger.info("end");
    }
}