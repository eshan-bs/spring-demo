package com.example.demo.repository;

import com.example.demo.model.MyUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<MyUser, Long> {

    Optional<MyUser> findMyUserByEmail(String email);

    Optional<MyUser> findMyUserByName(String name);

}
