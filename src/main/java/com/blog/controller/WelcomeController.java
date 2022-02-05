package com.blog.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {

    @GetMapping("/hello")
    public String HelloBlog(){
        return "Welcome to blog!!!";
    }

}
