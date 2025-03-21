package com.labprog.labprog.controllers;

import com.labprog.labprog.DTO.UserDTO;
import com.labprog.labprog.model.entities.Users;
import com.labprog.labprog.services.UserService;
//import com.labprog.labprog.services.utils.PasswordEncryptionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;


    @DeleteMapping("/{uid}")
    public ResponseEntity<String> deleteUser(@PathVariable UUID uid) {
        userService.deleteById(uid);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<List<Users>> getAllUsers() {
        List<Users> users = userService.findAll();
        return new ResponseEntity<>(users, HttpStatus.OK);

    }
    @GetMapping("/{uid}")
    public ResponseEntity<Users> getUser(@PathVariable UUID uid) {
        Optional<Users> user = userService.findById(uid);
        return new ResponseEntity<>(user.get(), HttpStatus.OK);

    }

    @GetMapping("/byUsername/{username}")
    public ResponseEntity<Users> getUserByUsername(@PathVariable String username) {
        Users user = userService.findByUsername(username);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PutMapping("/{uid}")
    public ResponseEntity<Users> updateUser(@PathVariable UUID uid, @RequestBody UserDTO userDTO) {
        Users user = new Users(userDTO);
        Users updatedUser = userService.update(uid, user);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);

    }

}
