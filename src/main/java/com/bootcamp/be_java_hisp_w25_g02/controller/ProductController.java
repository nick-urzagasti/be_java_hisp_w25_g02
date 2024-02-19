package com.bootcamp.be_java_hisp_w25_g02.controller;

import com.bootcamp.be_java_hisp_w25_g02.dto.response.PostDTO;
import com.bootcamp.be_java_hisp_w25_g02.entity.Post;
import com.bootcamp.be_java_hisp_w25_g02.service.IPostService;
import com.bootcamp.be_java_hisp_w25_g02.service.PostServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class ProductController {
    IPostService postService;
    public ProductController(PostServiceImpl postService){
        this.postService = postService;
    }

    @PostMapping("products/post")
    public ResponseEntity<?> savePost(@RequestBody PostDTO post){
        return new ResponseEntity<>(this.postService.savePost(post), HttpStatus.OK);
    }
}
