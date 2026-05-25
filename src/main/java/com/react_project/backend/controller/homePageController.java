package com.react_project.backend.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class homePageController {
    @GetMapping("/hello")
    public String getHomePage() {
        return "hello";
    }

}
