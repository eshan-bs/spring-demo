package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;
import com.example.demo.model.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findUsersByEmailOrName(String emailOrName);

}
