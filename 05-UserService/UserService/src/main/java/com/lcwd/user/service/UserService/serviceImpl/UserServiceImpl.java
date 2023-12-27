package com.lcwd.user.service.UserService.serviceImpl;

import com.lcwd.user.service.UserService.entity.Hotel;
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
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

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
        Rating[] ratingOfUser = restTemplate.getForObject("http://RATING-SERVICE/ratings/users/"+user.getUserId(),Rating[].class);

        List<Rating> ratingsList = Arrays.stream(ratingOfUser).toList();

        List<Rating> ratingList = ratingsList.stream().map(rating -> {
            // API call to hotel service to get Hotel
            ResponseEntity<Hotel> response = restTemplate.getForEntity("http://HOTEL-SERVICE/hotels/"+rating.getHotelId(),Hotel.class);
            Hotel hotel = response.getBody();
            // set the hotel to rating
            rating.setHotel(hotel);
            // return the rating with hotel detail
            return rating;
        }).collect(Collectors.toList());

        user.setRatings(ratingList);
        return user;
    }
}
