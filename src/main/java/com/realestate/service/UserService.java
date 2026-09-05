package com.realestate.service;

import com.realestate.entity.User;
import com.realestate.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public User register(User user) {
        return repository.save(user);
    }

    public List<User> getUsers() {
        return repository.findAll();
    }
}
