package com.example.demo.controller;

import com.example.demo.model.Post;
import com.example.demo.repository.PostRepository;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.thymeleaf.spring5.context.webflux.IReactiveDataDriverContextVariable;
import org.thymeleaf.spring5.context.webflux.ReactiveDataDriverContextVariable;
import reactor.core.publisher.Flux;


@Controller
public class ThymeleafController {

    private final PostRepository posts;

    public ThymeleafController(PostRepository posts) {
        this.posts = posts;
    }

    @RequestMapping({"/", "/thymeleaf"})
    public String index() {
        return "thymeleaf/index";
    }

    @RequestMapping("/smalllist.thymeleaf")
    public String smallList(final Model model) {
        model.addAttribute("entries", this.posts.findAll());
        return "thymeleaf/smalllist";
    }

//    @GetMapping(path="/index-sse", produces=MediaType.TEXT_EVENT_STREAM_VALUE)
    @RequestMapping("/index-sse")
    public String indexSse() {
        return "thymeleaf/index-sse";
    }

    @RequestMapping("/events")
    public String events(final Model model) {

        final Flux<Post> postlistStream = this.posts.findAll();

        final IReactiveDataDriverContextVariable dataDriver =
                new ReactiveDataDriverContextVariable(postlistStream, 1, 1);

        model.addAttribute("entries", dataDriver);

        return "thymeleaf/events";
    }


}