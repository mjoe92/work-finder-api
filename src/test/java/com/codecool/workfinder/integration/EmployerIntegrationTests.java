package com.codecool.workfinder.integration;

import com.codecool.workfinder.model.dto.EmployerDto;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@TestMethodOrder(value = MethodOrderer.OrderAnnotation.class)
public class EmployerIntegrationTests {

    @LocalServerPort
    private int port;
    private String baseUrl;

    @Autowired
    private TestRestTemplate restTemplate;

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
    public void registerNullEmployer_returnBadRequestStatus() throws IOException {
        HttpUriRequest request = new HttpPost(baseUrl);
        HttpResponse response = HttpClientBuilder.create().build().execute(request);
        assertEquals(response.getStatusLine().getStatusCode(), HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @Order(3)
    public void registerEmployer_returnResponseWithEmployer() {
        EmployerDto employerDto = getResponseOfPostRequest();
        ResponseEntity<?> entity = restTemplate.postForEntity(baseUrl, ResponseEntity.class, EmployerDto.class);
    }

    private EmployerDto getResponseOfPostRequest() {

        return null;
    }

    @Test
    @Order(4)
    public void findAllInRepo_returnResponseWithEmployerList() {

    }

    @Test
    @Order(5)
    public void findById_returnResponseWithEmployer() {

    }

    @Test
    @Order(6)
    public void deleteById_returnResponseWithDeletedEmployer() {

    }
}