package com.lcwd.user.service.UserService.controller;

import com.lcwd.user.service.UserService.entity.User;
import com.lcwd.user.service.UserService.service.UserService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;


@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User usr = userService.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(usr);
    }

    @GetMapping("/{userId}")
    @CircuitBreaker(name = "ratingHotelBreaker", fallbackMethod = "ratingHotelFallback")
    public ResponseEntity<User> getUserById(@PathVariable Long userId) {
        User user = userService.getUser(userId);
        return ResponseEntity.ok(user);

    }


    @GetMapping
    public ResponseEntity<List<User>> getAllUser() {
        List<User> userList = userService.getAllUser();
        return ResponseEntity.ok(userList);
    }

    ;

    // creating fall back method for circuit-breaker
    public ResponseEntity<User> ratingHotelFallback(Long userId, Exception e) {
        logger.info("Fallback is executed because service is down : ", e.getMessage());
        e.printStackTrace();
        User user = User.builder()
                .email("abc@gmail.com")
                .name("Abc")
                .about("This user is created dummy because some services are down.")
                .userId(555L).build();
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
