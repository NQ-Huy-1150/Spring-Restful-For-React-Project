package com.react_project.backend.controller;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class homePageController {
    @GetMapping("/hello")
    public String getHomePage() {
        return "hello from Jenkins CI/CD!";
    }

    @GetMapping("/ping")
    public Map<String, Object> ping() {
        Map<String, Object> res = new HashMap<>();
        res.put("status", "UP");
        res.put("message", "CI/CD Webhook deployment successful!");
        res.put("timestamp", LocalDateTime.now().toString());
        return res;
    }

    @GetMapping("/user")
    @PreAuthorize("hasRole('USER')")
    public String get(@RequestParam String param) {
        return new String();
    }

}
