package com.bootcamp.be_java_hisp_w25_g02.service;
import com.bootcamp.be_java_hisp_w25_g02.dto.response.UserFollowingDTO;
import java.util.List;

import java.util.List;
public interface IUserService {
    List<Integer> getfollowedUsersId(Integer userId);

    UserFollowingDTO getFollowedSellers(Integer userId);

}
