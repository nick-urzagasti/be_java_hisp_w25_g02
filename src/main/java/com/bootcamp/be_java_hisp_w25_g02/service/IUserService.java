package com.bootcamp.be_java_hisp_w25_g02.service;
import com.bootcamp.be_java_hisp_w25_g02.dto.response.UserFollowingDTO;
import java.util.List;

import com.bootcamp.be_java_hisp_w25_g02.entity.User;

import java.util.Optional;

public interface IUserService {

    UserFollowingDTO getFollowedSellers(Integer userId, String order);
    List<Integer> getfollowedUsersId(Integer userId);
    void followUser(Integer userId, Integer userIdToFollow);
    void unfollowUser(Integer userId, Integer userIdToUnfollow);
    boolean existUser(Integer id);
    boolean esVendedor(Integer id);
}
