package com.learning.springsecurity.JdbcSpringSecurity.Controller;


import com.learning.springsecurity.JdbcSpringSecurity.Configuration.Entity.MyUser;
import com.learning.springsecurity.JdbcSpringSecurity.Configuration.Entity.Repo.MyUserRepo;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SampleController {
    private MyUserRepo myUserRepo;
    private PasswordEncoder passwordEncoder;

    public SampleController(MyUserRepo myUserRepo  , PasswordEncoder passwordEncoder){
        this.myUserRepo = myUserRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/home")
    public String home(){
        return "home";
    }

    @GetMapping("/admin/home")
    public String admin(){
        return "userAdmin";
    }


    @GetMapping("/user/home")
    public String user(){
        return "userLogin";
    }


    @PostMapping("/register/user")
    @ResponseBody
    public MyUser createUser(@RequestBody MyUser myuser){
        myuser.setPassword(passwordEncoder.encode(myuser.getPassword()));
        return myUserRepo.save(myuser);


    }

    @GetMapping("/login")
    public String handleLogin(){
        return "custom_login.html";
    }



}
