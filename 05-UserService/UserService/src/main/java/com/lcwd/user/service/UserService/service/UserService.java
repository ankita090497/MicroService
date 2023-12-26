package com.lcwd.user.service.UserService.service;

import com.lcwd.user.service.UserService.entity.User;

import java.util.List;

public interface UserService {


    // create User
    User saveUser(User user);

    // get All User
    List<User> getAllUser();

    // get single user of given Id
    User getUser(Long userId);

}
