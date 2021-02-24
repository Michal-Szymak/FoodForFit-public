package com.objavieni.repository;

import com.objavieni.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    //User findByName(String name);
    Optional<User> findByName(String name);

}
