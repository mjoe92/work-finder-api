package com.codecool.workfinder.integration;

import com.codecool.workfinder.model.entity.Client;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.*;
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
public class ClientIntegrationTests {

    @LocalServerPort
    private int port;
    private String baseUrl;

    @Autowired
    private TestRestTemplate restTemplate;

    private final List<HttpEntity<Client>> clientEntities = generateEntities();

    private List<HttpEntity<Client>> generateEntities() {
        List<Client> clients = generateClients();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        List<HttpEntity<Client>> entities = new ArrayList<>();
        for (Client client : clients) {
            HttpEntity<Client> entity = new HttpEntity<>(client, headers);
            entities.add(entity);
        }
        return entities;
    }

    private List<Client> generateClients() {
        List<Client> clientList = new ArrayList<>();
        for (int i = 0; i <= 9; i++) {
            Client client = new Client();
            client.setId("7422ea93-844b-437b-8f40-3c6b82221e1" + i);
            client.setName("name_" + i);
            client.setEmail("email_" + i);
            clientList.add(client);
        }
        return clientList;
    }

    @BeforeEach
    public void setup() {
        this.baseUrl = String.format("http://localhost:%d/clients", port);
    }

    @Test
    @Order(1)
    public void checkGetAndPutAndDelete_withEmptyRepository_returnNotFoundStatus() throws IOException {
        HttpUriRequest[] requestList = new HttpUriRequest[] {
                new HttpGet(baseUrl),
                new HttpGet(baseUrl + "/falseId"),
                new HttpPut(baseUrl + "/falseId/job/falseJobId"),
                new HttpDelete(baseUrl + "/falseId")
        };
        for (HttpUriRequest request : requestList) {
            HttpResponse response = HttpClientBuilder.create().build().execute(request);
            assertEquals(response.getStatusLine().getStatusCode(), HttpStatus.NOT_FOUND.value());
        }
    }

    @Test
    @Order(2)
    public void registerNullClient_returnBadRequestStatus() throws IOException {
        HttpUriRequest request = new HttpPost(baseUrl);
        HttpResponse response = HttpClientBuilder.create().build().execute(request);
        assertEquals(response.getStatusLine().getStatusCode(), HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @Order(3)
    public void registerNewClient_returnResponseWithClient() {
        ResponseEntity<Client> testClientEntity = restTemplate
                .postForEntity(baseUrl, clientEntities.get(0), Client.class);
        assertNotNull(testClientEntity);
    }
}