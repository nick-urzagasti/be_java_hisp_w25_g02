package com.bootcamp.be_java_hisp_w25_g02.service;

import com.bootcamp.be_java_hisp_w25_g02.dto.response.FollowerCountDTO;

public interface IUserService {
    public FollowerCountDTO getUserTotalFollowers (Integer userId);

}
