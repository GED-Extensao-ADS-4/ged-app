package br.apae.ged.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/health")
public class HealthChecker {

    @GetMapping
    public String checker(){
        return "OK";
    }
}
