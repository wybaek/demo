package com.example.demo;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.springframework.web.reactive.function.client.ExchangeFilterFunctions.basicAuthentication;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

    @Autowired
    private ApplicationContext context;

    private WebTestClient client;

    @Before
    public void setup() {
        client = WebTestClient
                .bindToApplicationContext(context)
                .configureClient()
                .filter(basicAuthentication())
                .baseUrl("http://localhost:8080/")
                .build();
    }

    @Test
    public void getMoviesWhenNotAuthenticatedThenIsUnauthorized() {
        client
                .get()
                .uri("/home")
                .exchange()
                .expectStatus().is2xxSuccessful();
    }

}
