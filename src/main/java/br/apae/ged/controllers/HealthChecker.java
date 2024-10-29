package br.apae.ged.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/health")
public class HealthChecker {

    @GetMapping("/checker1")
    @Tag(name = "Saúde")
    @Operation(summary = "Este endpoint deve retornar uma String escrito 'OK 1'")
    public String checker(){
        return "OK";
    }

    @GetMapping("/checker2")
    @Tag(name = "Saúde")
    public String checker2(){
        return "OK 2";
    }
}
