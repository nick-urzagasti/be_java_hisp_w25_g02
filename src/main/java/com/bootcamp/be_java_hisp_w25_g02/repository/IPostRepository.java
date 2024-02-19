package com.bootcamp.be_java_hisp_w25_g02.repository;

import java.util.List;
import java.util.Optional;
import com.bootcamp.be_java_hisp_w25_g02.entity.Post;

public interface IPostRepository {
    Optional<Post> findById(Integer id);
    List<Post> findAll();
    List<Post> findByUserId(Integer userId);
}
