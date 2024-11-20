package com.learning.springsecurity.JdbcSpringSecurity.Configuration.Entity.Repo;

import com.learning.springsecurity.JdbcSpringSecurity.Configuration.Entity.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MyUserRepo extends JpaRepository<MyUser,Long> {

    Optional<MyUser> findByUserName(String userName);
}
