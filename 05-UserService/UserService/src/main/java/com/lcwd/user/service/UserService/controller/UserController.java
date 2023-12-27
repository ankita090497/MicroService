package com.lcwd.user.service.UserService.controller;

import com.lcwd.user.service.UserService.entity.User;
import com.lcwd.user.service.UserService.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user){
        User usr = userService.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(usr);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable Long userId){
        User user = userService.getUser(userId);
        return ResponseEntity.ok(user);

    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUser(){
        List<User> userList = userService.getAllUser();
        return ResponseEntity.ok(userList);
    };
}
