package com.bootcamp.be_java_hisp_w25_g02.service;

import com.bootcamp.be_java_hisp_w25_g02.dto.response.UserFollowingDTO;

import java.util.List;

public interface IUserService {
    UserFollowingDTO getFollowedSellers(Integer userId, String order);
}
