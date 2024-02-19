package com.bootcamp.be_java_hisp_w25_g02.service;

import com.bootcamp.be_java_hisp_w25_g02.dto.response.GenericResponseDTO;
import com.bootcamp.be_java_hisp_w25_g02.dto.response.PostDTO;
import com.bootcamp.be_java_hisp_w25_g02.entity.Post;
import com.bootcamp.be_java_hisp_w25_g02.dto.response.FollowingPostDTO;


public interface IPostService {
    GenericResponseDTO savePost(PostDTO post);

    FollowingPostDTO searchPostsOrderedByDate(Integer userId, String order);
}
