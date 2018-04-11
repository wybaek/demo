package com.example.demo;

import com.example.demo.model.Post;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.web.reactive.function.client.ExchangeFilterFunctions.basicAuthentication;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

    @Autowired
    private ApplicationContext context;

    private WebTestClient webClient;

//    @Autowired
//    private RouterFunction<ServerResponse> postFunction;

//    private static Map<String, Post> postMap = new HashMap<>();

    @Before
    public void setup() {

        webClient = WebTestClient
                .bindToApplicationContext(context)
                .configureClient()
                .filter(basicAuthentication())
                .baseUrl("http://localhost:8080/")
                .build();

    }

    @Test
    public void getHome() {
        webClient
                .get()
                .uri("/home")
                .exchange()
                .expectStatus().is2xxSuccessful();
    }

    @Test
    public void getStatus() throws Exception {
        webClient.get().uri("/health_check.html").accept(MediaType.TEXT_PLAIN).exchange()
                .expectBody(String.class).isEqualTo("Hello World");
    }

    @Test
    public void getAllPost() throws Exception {
//        webClient = WebTestClient.bindToRouterFunction(this.postFunction).build();
        webClient
                // Create a GET request to test an endpoint
                .get().uri("/all")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                // and use the dedicated DSL to test assertions against the response
                .expectStatus().isOk()
                .expectBodyList(Post.class)
                .hasSize(10)
//                .contains(postMap.get("title1"), postMap.get("title2"))
                .consumeWith( result -> result.getResponseBody().forEach(customer -> log.info(customer.toString())) );
    }

}
