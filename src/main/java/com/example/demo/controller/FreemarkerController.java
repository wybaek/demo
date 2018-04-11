package com.example.demo.controller;


import com.example.demo.repository.PostRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.Duration;

@Controller
public class FreemarkerController {

    private final PostRepository posts;

    public FreemarkerController(PostRepository posts) {
        this.posts = posts;
    }

    @GetMapping(path="/home")
    public String home(Model model) {
        model.addAttribute("posts", this.posts.findAll());
        return "home";
    }

    @GetMapping(path="/hello")
    public String hello(Model model) {
        model.addAttribute("hello", "Hi, Freemarker");
        return "hello";
    }

    @RequestMapping("/freemarker")
    public String index() {
        return "index";
    }


    @RequestMapping("/smalllist.freemarker")
    public String smallList(final Model model) {

        model.addAttribute("entries", this.posts.findAll());
        return "smalllist";
    }
}
