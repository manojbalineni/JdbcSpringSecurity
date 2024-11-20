package com.learning.springsecurity.JdbcSpringSecurity.Configuration.Entity.service;

import com.learning.springsecurity.JdbcSpringSecurity.Configuration.Entity.MyUser;
import com.learning.springsecurity.JdbcSpringSecurity.Configuration.Entity.Repo.MyUserRepo;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailService implements UserDetailsService {

    private MyUserRepo userRepo;

    public MyUserDetailService(MyUserRepo userRepo){
        this.userRepo = userRepo;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<MyUser> user = userRepo.findByUserName(username);
        if(user.isPresent()){
           return User.builder()
                   .username(user.get().getUserName())
                   .password(user.get().getPassword())
                   .roles(getRoles(user.get().getRole()))
                   .build();

        }
        else{
            throw  new UsernameNotFoundException("User Not Found");
        }

    }

    private String[] getRoles(String role) {
        if(role == null){
            return  new String[]{"USER"};
        }
        else{
            return role.split(",");

        }
    }
}
