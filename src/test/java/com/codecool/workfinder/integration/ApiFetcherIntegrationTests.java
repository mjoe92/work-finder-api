package com.codecool.workfinder.integration;

import com.codecool.workfinder.model.api.adzuna.AdzunaJobCollection;
import com.codecool.workfinder.model.entity.Job;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.util.AssertionErrors.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@TestMethodOrder(value = MethodOrderer.OrderAnnotation.class)
public class ApiFetcherIntegrationTests {

    @LocalServerPort
    private int port;
    private String baseUrl;

    @Autowired
    private TestRestTemplate restTemplate;
/*
    private final Map<String, String> apiUrls = new HashMap<>() {{
        put("adzuna", String.format("http://localhost:%d/%s", port, apiName));
    }};
*/
    private final static String apiName = "adzuna";

    @BeforeEach
    public void setup() {
        this.baseUrl = String.format("http://localhost:%d/%s", port, apiName);
    }

    @Test
    @Order(1)
    public void getOKStatusFromApi_ofAdzuna() throws IOException {
        HttpUriRequest request = new HttpGet(baseUrl);
        HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
        assertEquals(httpResponse.getStatusLine().getStatusCode(), HttpStatus.OK.value());
    }

    @Test
    @Order(2)
    public void checkJobFieldsFromApi_fetchList_asAdzunaJobCollection() {
        AdzunaJobCollection apiJob = restTemplate
                .getForObject(baseUrl, AdzunaJobCollection.class);
        List<Job> jobs = apiJob.toJobList();
        Job randomJob = jobs.get(0);

        assertNotNull(randomJob.getLocation());
        assertNotNull(randomJob.getDescription());
        assertNotNull(randomJob.getTitle());
        assertNotNull(randomJob.getUrl());
    }
}
