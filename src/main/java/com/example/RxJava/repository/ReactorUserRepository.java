package com.example.RxJava.repository;

import com.example.RxJava.model.User;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface ReactorUserRepository extends ReactiveCrudRepository<User, Long> {
}