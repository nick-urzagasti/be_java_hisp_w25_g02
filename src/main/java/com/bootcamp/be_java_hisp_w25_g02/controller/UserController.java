package com.bootcamp.be_java_hisp_w25_g02.controller;

import com.bootcamp.be_java_hisp_w25_g02.dto.response.FollowerCountDTO;
import com.bootcamp.be_java_hisp_w25_g02.service.IUserService;
import com.bootcamp.be_java_hisp_w25_g02.service.UserServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private final IUserService userService;

    public UserController(UserServiceImpl userService, IUserService userService1) {
        this.userService = userService1;
    }

    @GetMapping("/users/{userId}/followers/count")
    public ResponseEntity<FollowerCountDTO> getUserTotalFollowers (@PathVariable Integer userId){
        return new ResponseEntity<>(userService.getUserTotalFollowers(userId), HttpStatus.OK);
    }

}