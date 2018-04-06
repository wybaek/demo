package com.example.demo.controller;


import com.example.demo.model.Post;
import com.example.demo.repository.PostRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
class RestApiController {

    private final PostRepository posts;

    public RestApiController(PostRepository posts) {
        this.posts = posts;
    }

    @GetMapping(path="/all")
    @ResponseBody
    public Flux<Post> all() {
        return this.posts.findAll();
    }
}

