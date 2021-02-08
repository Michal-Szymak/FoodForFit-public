package com.objavieni.repository;

import com.objavieni.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

public interface UserRepository extends JpaRepository<User,Long> {

    public User findByName(String name);

}
