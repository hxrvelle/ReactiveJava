package com.example.RxJava.service;

import com.example.RxJava.model.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ReactorUserService {
    Flux<User> getAllUsers();
    Mono<User> getUserById(Long id);
    Mono<User> addUser(User user);
    Mono<User> updateUser(Long id, User updatedUser);
    Mono<Void> deleteUser(Long id);
}
