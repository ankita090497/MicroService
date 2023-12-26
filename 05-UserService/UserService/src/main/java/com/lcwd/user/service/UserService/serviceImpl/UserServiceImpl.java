package com.lcwd.user.service.UserService.serviceImpl;

import com.lcwd.user.service.UserService.entity.Rating;
import com.lcwd.user.service.UserService.entity.User;
import com.lcwd.user.service.UserService.exception.ResourceNotFoundException;
import com.lcwd.user.service.UserService.repository.UserRepository;
import com.lcwd.user.service.UserService.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.lang.module.ResolutionException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    private Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
    @Override

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public User getUser(Long userId) {
        User user =  userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User with given id is not found on server !! " + userId));
        // fetch rating of the above user from Rating service
       // http://localhost:9093/rating/user/34684b22-cbaf-4ddd-9a35-9f3293c5175c
        ArrayList<Rating> ratingOfUser = restTemplate.getForObject("http://localhost:8093/ratings/users/"+user.getUserId(),ArrayList.class);
        log.info("Rating : "+ratingOfUser);
        user.setRatings(ratingOfUser);
        return user;
    }
}
