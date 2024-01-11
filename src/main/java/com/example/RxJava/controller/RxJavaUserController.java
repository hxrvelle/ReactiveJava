package com.example.RxJava.controller;

import com.example.RxJava.model.User;
import com.example.RxJava.service.RxJavaUserService;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Single;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rxjava/users")
public class RxJavaUserController {
    private final RxJavaUserService rxJavaUserService;

    public RxJavaUserController(RxJavaUserService rxJavaUserService) {
        this.rxJavaUserService = rxJavaUserService;
    }

    @GetMapping
    public Flowable<User> getAllUsers() {
        return rxJavaUserService.getAllUsers();
    }

    @GetMapping("/{id}")
    public Maybe<User> getUserById(@PathVariable Long id) {
        return rxJavaUserService.getUserById(id);
    }

    @PostMapping
    public Single<User> addUser(@RequestBody User user) {
        return rxJavaUserService.addUser(user);
    }

    @PutMapping("/{id}")
    public Maybe<User> updateUser(@PathVariable Long id, @RequestBody User updatedUser) {
        return rxJavaUserService.updateUser(id, updatedUser);
    }

    @DeleteMapping("/{id}")
    public Completable deleteUser(@PathVariable Long id) {
        return rxJavaUserService.deleteUser(id);
    }
}