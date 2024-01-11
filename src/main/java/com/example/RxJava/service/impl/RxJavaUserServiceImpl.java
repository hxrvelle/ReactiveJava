package com.example.RxJava.service.impl;

import com.example.RxJava.controller.dto.UserIncomingDto;
import com.example.RxJava.controller.dto.UserOutgoingDto;
import com.example.RxJava.controller.mapper.UserMapper;
import com.example.RxJava.model.User;
import com.example.RxJava.repository.RxJavaUserRepository;
import com.example.RxJava.service.RxJavaUserService;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Single;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class RxJavaUserServiceImpl implements RxJavaUserService {
    private final RxJavaUserRepository rxJavaUserRepository;
    private final UserMapper userMapper;

    @Autowired
    public RxJavaUserServiceImpl(RxJavaUserRepository rxJavaUserRepository, UserMapper userMapper) {
        this.rxJavaUserRepository = rxJavaUserRepository;
        this.userMapper = userMapper;
    }

    @Override
    public Flowable<UserOutgoingDto> getAllUsers() {
        log.info("Getting all users");
        return rxJavaUserRepository.findAll()
                .map(userMapper::userToUserOutgoingDto)
                .doOnComplete(() -> log.info("Successfully retrieved all users"))
                .doOnError(error -> log.error("Error retrieving all users", error));
    }

    @Override
    public Maybe<UserOutgoingDto> getUserById(Long id) {
        log.info("Getting user by id: {}", id);
        return rxJavaUserRepository.findById(id)
                .map(userMapper::userToUserOutgoingDto)
                .doOnSuccess(user -> log.info("Successfully retrieved user with id: {}", id))
                .doOnError(error -> log.error("Error retrieving user with id: {}", id, error));
    }

    @Override
    public Single<UserOutgoingDto> addUser(UserIncomingDto userIncomingDto) {
        log.info("Adding user: {}", userIncomingDto);
        User user = userMapper.userIncomingDtoToUser(userIncomingDto);
        return rxJavaUserRepository.save(user)
                .map(userMapper::userToUserOutgoingDto)
                .doOnSuccess(savedUser -> log.info("Successfully added user: {}", savedUser))
                .doOnError(error -> log.error("Error adding user: {}", userIncomingDto, error));
    }

    @Override
    public Maybe<UserOutgoingDto> updateUser(Long id, UserIncomingDto userIncomingDto) {
        log.info("Updating user with id: {}", id);
        User user = userMapper.userIncomingDtoToUser(userIncomingDto);
        return rxJavaUserRepository.findById(id)
                .flatMap(existingUser -> {
                    existingUser.setName(user.getName());
                    existingUser.setAge(user.getAge());
                    return rxJavaUserRepository.save(existingUser).toMaybe();
                })
                .map(userMapper::userToUserOutgoingDto)
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