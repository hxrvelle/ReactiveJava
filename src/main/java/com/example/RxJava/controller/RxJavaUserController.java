package com.example.RxJava.controller;

import com.example.RxJava.controller.dto.UserIncomingDto;
import com.example.RxJava.controller.dto.UserOutgoingDto;
import com.example.RxJava.service.RxJavaUserService;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Single;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    public RxJavaUserController(RxJavaUserService rxJavaUserService) {
        this.rxJavaUserService = rxJavaUserService;
    }

    @GetMapping
    public Flowable<UserOutgoingDto> getAllUsers() {
        return rxJavaUserService.getAllUsers();
    }

    @GetMapping("/{id}")
    public Maybe<UserOutgoingDto> getUserById(@PathVariable Long id) {
        return rxJavaUserService.getUserById(id);
    }

    @PostMapping
    public Single<UserOutgoingDto> addUser(@RequestBody UserIncomingDto userIncomingDto) {
        return rxJavaUserService.addUser(userIncomingDto);
    }

    @PutMapping("/{id}")
    public Maybe<UserOutgoingDto> updateUser(@PathVariable Long id, @RequestBody UserIncomingDto userIncomingDto) {
        return rxJavaUserService.updateUser(id, userIncomingDto);
    }

    @DeleteMapping("/{id}")
    public Completable deleteUser(@PathVariable Long id) {
        return rxJavaUserService.deleteUser(id);
    }
}