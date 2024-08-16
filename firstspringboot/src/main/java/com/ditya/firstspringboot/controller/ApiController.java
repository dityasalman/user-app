package com.ditya.firstspringboot.controller;

import com.ditya.firstspringboot.models.User;
import com.ditya.firstspringboot.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ApiController {

    @Autowired
    private UserRepo userRepo;

    @GetMapping("/")
    public String getPage(){
        return "what's up brother? ☝️";
    }

    @GetMapping("/users")
    public List<User> getUser() {
        return userRepo.findAll();
    }

    @PostMapping("/save")
    public String saveUser(@RequestBody User user) {
        userRepo.save(user);
        return "saved bruvv...";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable long id){
        User deleteUser = userRepo.findById(id).get();
        userRepo.delete(deleteUser);
        return "user deleted";
    }
}
