package com.example.RxJava.controller;

import com.example.RxJava.model.User;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import com.example.RxJava.service.ReactorUserService;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/reactor/users")
public class ReactorUserController {
    private final ReactorUserService reactorUserService;

    public ReactorUserController(ReactorUserService reactorUserService) {
        this.reactorUserService = reactorUserService;
    }

    @GetMapping
    public Flux<User> getAllUsers() {
        return reactorUserService.getAllUsers();
    }

    @GetMapping("/{id}")
    public Mono<User> getUserById(@PathVariable Long id) {
        return reactorUserService.getUserById(id);
    }

    @PostMapping
    public Mono<User> addUser(@RequestBody User user) {
        return reactorUserService.addUser(user);
    }

    @PutMapping("/{id}")
    public Mono<User> updateUser(@PathVariable Long id, @RequestBody User updatedUser) {
        return reactorUserService.updateUser(id, updatedUser);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteUser(@PathVariable Long id) {
        return reactorUserService.deleteUser(id);
    }
}
