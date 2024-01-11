package com.example.RxJava.service;

import com.example.RxJava.controller.dto.UserIncomingDto;
import com.example.RxJava.controller.dto.UserOutgoingDto;
import com.example.RxJava.model.User;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Single;

public interface RxJavaUserService {
    Flowable<UserOutgoingDto> getAllUsers();
    Maybe<UserOutgoingDto> getUserById(Long id);
    Single<UserOutgoingDto> addUser(UserIncomingDto userIncomingDto);
    Maybe<UserOutgoingDto> updateUser(Long id, UserIncomingDto userIncomingDto);
    Completable deleteUser(Long id);
}
