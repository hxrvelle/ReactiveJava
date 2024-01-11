package com.example.RxJava.repository;

import com.example.RxJava.model.User;
import org.springframework.data.repository.reactive.RxJava3CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RxJavaUserRepository extends RxJava3CrudRepository<User, Long> {
}
