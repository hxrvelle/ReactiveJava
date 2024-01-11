package com.example.RxJava.service.impl;

import com.example.RxJava.model.User;
import com.example.RxJava.repository.RxJavaUserRepository;
import com.example.RxJava.service.RxJavaUserService;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Single;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class RxJavaUserServiceImpl implements RxJavaUserService {
    private final RxJavaUserRepository rxJavaUserRepository;

    public RxJavaUserServiceImpl(RxJavaUserRepository rxJavaUserRepository) {
        this.rxJavaUserRepository = rxJavaUserRepository;
    }

    @Override
    public Flowable<User> getAllUsers() {
        log.info("Getting all users");
        return rxJavaUserRepository.findAll()
                .doOnComplete(() -> log.info("Successfully retrieved all users"))
                .doOnError(error -> log.error("Error retrieving all users", error));
    }

    @Override
    public Maybe<User> getUserById(Long id) {
        log.info("Getting user by id: {}", id);
        return rxJavaUserRepository.findById(id)
                .doOnSuccess(user -> log.info("Successfully retrieved user with id: {}", id))
                .doOnError(error -> log.error("Error retrieving user with id: {}", id, error));
    }

    @Override
    public Single<User> addUser(User user) {
        log.info("Adding user: {}", user);
        return rxJavaUserRepository.save(user)
                .doOnSuccess(savedUser -> log.info("Successfully added user: {}", savedUser))
                .doOnError(error -> log.error("Error adding user: {}", user, error));
    }

    @Override
    public Maybe<User> updateUser(Long id, User updatedUser) {
        log.info("Updating user with id: {}", id);
        return rxJavaUserRepository.findById(id)
                .flatMap(existingUser -> {
                    existingUser.setName(updatedUser.getName());
                    existingUser.setAge(updatedUser.getAge());
                    return rxJavaUserRepository.save(existingUser).toMaybe();
                })
                .doOnSuccess(updated -> log.info("Successfully updated user with id: {}", id))
                .doOnError(error -> log.error("Error updating user with id: {}", id, error));
    }

    @Override
    public Completable deleteUser(Long id) {
        log.info("Deleting user with id: {}", id);
        return rxJavaUserRepository.deleteById(id)
                .doOnComplete(() -> log.info("Successfully deleted user with id: {}", id))
                .doOnError(error -> log.error("Error deleting user with id: {}", id, error));
    }
}