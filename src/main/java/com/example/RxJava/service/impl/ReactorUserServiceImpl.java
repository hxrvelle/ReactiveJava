package com.example.RxJava.service.impl;

import com.example.RxJava.model.User;
import com.example.RxJava.repository.ReactorUserRepository;
import com.example.RxJava.service.ReactorUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class ReactorUserServiceImpl implements ReactorUserService {
    private final ReactorUserRepository userRepository;

    public ReactorUserServiceImpl(ReactorUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Flux<User> getAllUsers() {
        log.info("Getting all users");
        return userRepository.findAll()
                .doOnComplete(() -> log.info("Successfully retrieved all users"))
                .doOnError(error -> log.error("Error retrieving all users", error));
    }

    @Override
    public Mono<User> getUserById(Long id) {
        log.info("Getting user by id: {}", id);
        return userRepository.findById(id)
                .doOnSuccess(user -> log.info("Successfully retrieved user with id: {}", id))
                .doOnError(error -> log.error("Error retrieving user with id: {}", id, error));
    }

    @Override
    public Mono<User> addUser(User user) {
        log.info("Adding user: {}", user);
        return userRepository.save(user)
                .doOnSuccess(savedUser -> log.info("Successfully added user: {}", savedUser))
                .doOnError(error -> log.error("Error adding user: {}", user, error));
    }

    @Override
    public Mono<User> updateUser(Long id, User updatedUser) {
        log.info("Updating user with id: {}", id);
        return userRepository.findById(id)
                .flatMap(existingUser -> {
                    existingUser.setName(updatedUser.getName());
                    existingUser.setAge(updatedUser.getAge());
                    return userRepository.save(existingUser);
                })
                .doOnSuccess(updated -> log.info("Successfully updated user with id: {}", id))
                .doOnError(error -> log.error("Error updating user with id: {}", id, error));
    }

    @Override
    public Mono<Void> deleteUser(Long id) {
        log.info("Deleting user with id: {}", id);
        return userRepository.deleteById(id)
                .doOnSuccess(v -> log.info("Successfully deleted user with id: {}", id))
                .doOnError(error -> log.error("Error deleting user with id: {}", id, error));
    }
}
