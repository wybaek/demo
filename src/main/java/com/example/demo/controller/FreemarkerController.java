package com.example.demo.controller;


import com.example.demo.repository.PostRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.Duration;

@Controller
public class FreemarkerController {

    private final PostRepository posts;

    public FreemarkerController(PostRepository posts) {
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
