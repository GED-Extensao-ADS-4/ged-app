package br.apae.ged.controllers;

import br.apae.ged.dto.user.UserLoginDTO;
import br.apae.ged.dto.user.UserRequestDTO;
import br.apae.ged.dto.user.UserResponse;
import br.apae.ged.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@RequestBody UserRequestDTO entity) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.register(entity));
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserLoginDTO entity) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.login(entity));
    }

    @PutMapping("/setAdmin/{id}")
    public ResponseEntity<Void> setAdminRole(@PathVariable("id")Long id){
        service.setAdminRole(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/removeAdmin/{id}")
    public ResponseEntity<Void> removeAdminRole(@PathVariable("id")Long id){
        service.removeAdminRole(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> desativarUser(@PathVariable("id")Long id){
        service.changeStatusUser(id);
        return ResponseEntity.ok().build();
    }

}
