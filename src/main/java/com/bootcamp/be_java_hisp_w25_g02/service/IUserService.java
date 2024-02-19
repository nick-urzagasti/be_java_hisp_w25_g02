package com.bootcamp.be_java_hisp_w25_g02.service;
import com.bootcamp.be_java_hisp_w25_g02.dto.response.UserFollowingDTO;
import java.util.List;

import com.bootcamp.be_java_hisp_w25_g02.entity.User;

import java.util.Optional;

public interface IUserService {
    public boolean existUser(long id);
    public boolean esVendedor(long id);
    List<Integer> getfollowedUsersId(Integer userId);

    UserFollowingDTO getFollowedSellers(Integer userId);

    void followUser(Integer userId, Integer userIdToFollow);
    void unfollowUser(Integer userId, Integer userIdToUnfollow);


}
