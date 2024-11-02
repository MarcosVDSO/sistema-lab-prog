package com.labprog.labprog.controller;

import com.labprog.labprog.user.User;
import com.labprog.labprog.user.UserRepository;
import com.labprog.labprog.user.UserRequestDTO;
import com.labprog.labprog.user.UserResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserRepository repository;
    @GetMapping
    public List<UserResponseDTO> getAll() {
        return repository.findAll().stream().map(UserResponseDTO::new).toList();
    }

    @PostMapping
    public void addUser(@RequestBody UserRequestDTO data) {
        User user = new User(data);
        repository.save(user);
        return;
    }
}
