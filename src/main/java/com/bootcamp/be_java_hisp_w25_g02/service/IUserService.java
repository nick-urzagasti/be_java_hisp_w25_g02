package com.bootcamp.be_java_hisp_w25_g02.service;

import java.util.List;

public interface IUserService {
    List<Integer> getfollowedUsersId(Integer userId);
}
