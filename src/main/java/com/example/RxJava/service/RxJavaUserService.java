package com.example.RxJava.service;

import com.example.RxJava.model.User;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Single;

public interface RxJavaUserService {
    Flowable<User> getAllUsers();
    Maybe<User> getUserById(Long id);
    Single<User> addUser(User user);
    Maybe<User> updateUser(Long id, User updatedUser);
    Completable deleteUser(Long id);
}
