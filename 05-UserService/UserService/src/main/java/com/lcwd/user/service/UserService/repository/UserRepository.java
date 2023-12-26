package com.lcwd.user.service.UserService.repository;

import com.lcwd.user.service.UserService.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {


}
