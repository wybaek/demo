package com.example.demo;

import java.time.Duration;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

// https://github.com/hantsy/spring-reactive-sample/tree/e6f14c6a003dc19b346efe0b0415db749783415f/boot-mvc-freemarker
@SpringBootApplication
@EnableMongoAuditing
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
}

@Component
@Slf4j
class DataInitializer implements CommandLineRunner {

    private final PostRepository posts;

    public DataInitializer(PostRepository posts) {
        this.posts = posts;
    }

    @Override
    public void run(String[] args) {
        log.info("start data initialization  ...");
        this.posts
                .deleteAll()
                .thenMany(
                        Flux
                                .range(1, 10)
                                .flatMap(
                                        num -> this.posts.save(Post.builder().title("Title" + num).content("content of " + "Title" + num).build())
                                )
                )
                .log()
                .subscribe(
                        null,
                        null,
                        () -> log.info("done initialization...")
                );

    }

}

@RestController
class AllController {

    private final PostRepository posts;

    public AllController(PostRepository posts) {
        this.posts = posts;
    }

    @GetMapping(path="/all", produces=MediaType.TEXT_EVENT_STREAM_VALUE)
    @ResponseBody
    public Flux<Post> all() {
        return this.posts.findAll();
    }
}

@Controller
class HomeController {

    private final PostRepository posts;

    public HomeController(PostRepository posts) {
        this.posts = posts;
    }

    @GetMapping(path="/home")
    public String home(Model model) {
        model.addAttribute("posts", this.posts.findAll().collectList().block(Duration.ofSeconds(1)));
        return "home";
    }

    @GetMapping(path="/hello")
    public String hello(Model model) {
        model.addAttribute("hello", "Hi, Freemarker");
        return "hello";
    }

}


interface PostRepository extends ReactiveMongoRepository<Post, String> {
}

@Document
@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
class Post {

    @Id
    private String id;
    private String title;
    private String content;

    @CreatedDate
    private LocalDateTime createdDate;
}