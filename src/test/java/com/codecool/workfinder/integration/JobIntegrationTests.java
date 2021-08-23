package com.codecool.workfinder.integration;

import com.codecool.workfinder.model.entity.Job;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.annotation.DirtiesContext;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@TestMethodOrder(value = MethodOrderer.OrderAnnotation.class)
public class JobIntegrationTests {

    @LocalServerPort
    private int port;
    private String baseUrl;

    @Autowired
    private TestRestTemplate restTemplate;

    private final List<HttpEntity<Job>> jobEntities = generateEntities();

    public List<HttpEntity<Job>> generateEntities() {
        List<Job> jobs = generateJobs();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        List<HttpEntity<Job>> entities = new ArrayList<>();
        for (Job job : jobs) {
            HttpEntity<Job> entity = new HttpEntity<>(job, headers);
            entities.add(entity);
        }
        return entities;
    }

    private List<Job> generateJobs() {
        List<Job> jobList = new ArrayList<>();
        for (int i = 0; i <= 9; i++) {
            Job job = new Job();
            job.setId("3fa85f64-5717-4562-b3fc-2c963f66afa" + i);
            job.setCategory("category_" + i);
            job.setTitle("title_" + i);
            job.setLocation("location_" + i);
            job.setCreated("created_" + i);
            job.setDescription("description_" + i);
            job.setCompany("company_" + i);
            job.setContractTime("contractTime_" + i);
            job.setUrl("url_" + i);
            job.setMinSalary(1000 * i);
            job.setMaxSalary(10000 * i);
            jobList.add(job);
        }
        return jobList;
    }

    @BeforeEach
    public void setup() {
        this.baseUrl = String.format("http://localhost:%d/employers", port);
    }

    @Test
    @Order(1)
    public void checkGetAndDelete_withEmptyRepository_returnNotFoundStatus() throws IOException {
        HttpUriRequest[] requestList = new HttpUriRequest[] {
                new HttpGet(baseUrl),
                new HttpGet(baseUrl + "/falseId"),
                new HttpDelete(baseUrl + "/falseId")
        };
        for (HttpUriRequest request : requestList) {
            HttpResponse response = HttpClientBuilder.create().build().execute(request);
            assertEquals(response.getStatusLine().getStatusCode(), HttpStatus.NOT_FOUND.value());
        }
    }

    @Test
    @Order(2)
    public void registerNullJob_returnBadRequestStatus() throws IOException {
        HttpUriRequest request = new HttpPost(baseUrl);
        HttpResponse response = HttpClientBuilder.create().build().execute(request);
        assertEquals(response.getStatusLine().getStatusCode(), HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @Order(3)
    public void registerNewJob_returnResponseWithJob() {
        ResponseEntity<Job> testJobEntity = restTemplate
                .postForEntity(baseUrl, jobEntities.get(0), Job.class);
        assertNotNull(testJobEntity);
    }
}