package ru.netology.springback;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import org.springframework.http.ResponseEntity;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import ru.netology.springback.resources.Amount;
import ru.netology.springback.resources.Transfer;

import static ru.netology.springback.resources.Currencies.RUR;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SpringBackApplicationTests {
//    @Autowired
//    TestRestTemplate restTemplate;
//    @Container
//    private static final GenericContainer<?> backApp = new GenericContainer<>("backapp:latest")
//            .withExposedPorts(8080);
//    @BeforeAll
//    public static void setUp() {
//        backApp.start();
//    }
    @Test
    public void contextLoads() {
//        Transfer transfer = new Transfer("2222222222222222",
//                "12/34",
//                "567",
//                "1111111111111111",
//                new Amount(5000, RUR));
//        ResponseEntity<String> forEntityDev = restTemplate.postForEntity("http://localhost:" + backApp.getMappedPort(8080) + "/transfer", transfer, String.class);
//        Assertions.assertEquals("{\"operationId\":\"1\"}", forEntityDev.getBody());
    }
}
