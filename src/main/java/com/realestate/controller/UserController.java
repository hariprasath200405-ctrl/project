package com.realestate.controller;

import com.realestate.entity.User;
import com.realestate.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@CrossOrigin
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping("/register")
    public User register(@RequestBody User user) {
        return service.register(user);
    }

    @GetMapping
    public List<User> getUsers() {
        return service.getUsers();
    }
}
