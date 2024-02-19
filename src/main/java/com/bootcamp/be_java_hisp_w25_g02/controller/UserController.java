package com.bootcamp.be_java_hisp_w25_g02.controller;

import com.bootcamp.be_java_hisp_w25_g02.dto.response.FollowerListDTO;
import com.bootcamp.be_java_hisp_w25_g02.dto.response.UserFollowingDTO;
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
    @GetMapping("/users/{userId}/followed/list")
    public ResponseEntity<UserFollowingDTO> getFollowedSellers(@PathVariable Integer userId) {
        return new ResponseEntity<>(userService.getFollowedSellers(userId), HttpStatus.OK);
    }

    @GetMapping("/user/{userId}/followers")
    public ResponseEntity<?> getFollowersList(@PathVariable Integer userId){
        System.out.println("Entro por aca");
        System.out.println("buscando info usuario: " + userId);
        FollowerListDTO followersList = userService.getFollowersList(userId);
        return new ResponseEntity<>(followersList, HttpStatus.OK);
    }
}
