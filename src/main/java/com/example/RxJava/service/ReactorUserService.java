package com.example.RxJava.service;

import com.example.RxJava.controller.dto.UserIncomingDto;
import com.example.RxJava.controller.dto.UserOutgoingDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ReactorUserService {
    Flux<UserOutgoingDto> getAllUsers();
    Mono<UserOutgoingDto> getUserById(Long id);
    Mono<UserOutgoingDto> addUser(UserIncomingDto userIncomingDto);
    Mono<UserOutgoingDto> updateUser(Long id, UserIncomingDto userIncomingDto);
    Mono<Void> deleteUser(Long id);
}
