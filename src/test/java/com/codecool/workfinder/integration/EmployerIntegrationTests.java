package com.codecool.workfinder.integration;

import com.codecool.workfinder.model.entity.Employer;
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
public class EmployerIntegrationTests {

    @LocalServerPort
    private int port;
    private String baseUrl;

    @Autowired
    private TestRestTemplate restTemplate;

    private final List<HttpEntity<Employer>> employerEntities = generateEntities();

    public List<HttpEntity<Employer>> generateEntities() {
        List<Employer> employers = generateEmployers();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        List<HttpEntity<Employer>> entities = new ArrayList<>();
        for (Employer employer : employers) {
            HttpEntity<Employer> entity = new HttpEntity<>(employer, headers);
            entities.add(entity);
        }
        return entities;
    }

    private List<Employer> generateEmployers() {
        List<Employer> employerList = new ArrayList<>();
        for (int i = 0; i <= 9; i++) {
            Employer employer = new Employer();
            employer.setId("V1StGXR8_Z5jdHi6B-my" + i);
            employer.setName("name_" + i);
            employer.setEmail("email_" + i);
            employer.setCompany("company_" + i);
            employerList.add(employer);
        }
        return employerList;
    }

    @BeforeEach
    void setup() {
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
    public void registerNullEmployer_returnBadRequestStatus() throws IOException {
        HttpUriRequest request = new HttpPost(baseUrl);
        HttpResponse response = HttpClientBuilder.create().build().execute(request);
        assertEquals(response.getStatusLine().getStatusCode(), HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @Order(3)
    public void registerNewEmployer_returnResponseWithEmployer() {
        ResponseEntity<Employer> testEmployer = restTemplate
                .postForEntity(baseUrl, employerEntities.get(0), Employer.class);
        assertNotNull(testEmployer);
    }
}