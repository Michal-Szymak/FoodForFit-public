package com.objavieni.repository;

import com.objavieni.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {

    public User findByName(String name);

}
