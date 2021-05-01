package com.rlrio.phonebook.controller;

import com.rlrio.phonebook.dto.UserDto;
import com.rlrio.phonebook.model.User;
import com.rlrio.phonebook.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.validation.Valid;
import java.util.List;

@RestController
public class UserController {
    private static final Logger log = LogManager.getLogger(UserController.class);
    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping({"/users/add"})
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody User user) {
        return new ResponseEntity<>(this.service.create(user), HttpStatus.CREATED);
    }

    @PutMapping({"/users/{userId}"})
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<UserDto> updateUser(@PathVariable("userId") int userId, @Valid @RequestBody User user) {
        return ResponseEntity.ok(this.service.update(userId, user));
    }

    @DeleteMapping({"/users/{userId}"})
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable("userId") int userId) {
        try {
            this.service.delete(userId);
        } catch (Exception e) {
            log.warn("The exception: {} occurred while deleting userId:{}", e.getStackTrace(), userId);
        }
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @GetMapping({"/users"})
    public ResponseEntity<List<UserDto>> getAllUsers() {
        return ResponseEntity.ok(this.service.getAll());
    }

    @GetMapping({"/users/{userId}"})
    public ResponseEntity<UserDto> getUserById(@PathVariable("userId") int userId) {
        return ResponseEntity.ok(this.service.findById(userId));
    }

    @GetMapping({"/users/search"})
    public ResponseEntity<List<UserDto>> getUserByName(@RequestParam String name) {
        return ResponseEntity.ok(this.service.findByName(name));
    }

}
