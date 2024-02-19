package com.bootcamp.be_java_hisp_w25_g02.service;

import com.bootcamp.be_java_hisp_w25_g02.dto.response.FollowingPostDTO;

public interface IPostService {
    FollowingPostDTO searchPostsOrderedByDate(Long userId, String order);
}
