package com.example.RxJava.controller;

import com.example.RxJava.controller.dto.UserIncomingDto;
import com.example.RxJava.controller.dto.UserOutgoingDto;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    public ReactorUserController(ReactorUserService reactorUserService) {
        this.reactorUserService = reactorUserService;
    }

    @GetMapping
    public Flux<UserOutgoingDto> getAllUsers() {
        return reactorUserService.getAllUsers();
    }

    @GetMapping("/{id}")
    public Mono<UserOutgoingDto> getUserById(@PathVariable Long id) {
        return reactorUserService.getUserById(id);
    }

    @PostMapping
    public Mono<UserOutgoingDto> addUser(@RequestBody UserIncomingDto userIncomingDto) {
        return reactorUserService.addUser(userIncomingDto);
    }

    @PutMapping("/{id}")
    public Mono<UserOutgoingDto> updateUser(@PathVariable Long id, @RequestBody UserIncomingDto userIncomingDto) {
        return reactorUserService.updateUser(id, userIncomingDto);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteUser(@PathVariable Long id) {
        return reactorUserService.deleteUser(id);
    }
}
