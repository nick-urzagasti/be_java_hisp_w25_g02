package com.bootcamp.be_java_hisp_w25_g02.controller;

import com.bootcamp.be_java_hisp_w25_g02.dto.response.FollowingPostDTO;
import com.bootcamp.be_java_hisp_w25_g02.entity.Post;
import com.bootcamp.be_java_hisp_w25_g02.entity.User;
import com.bootcamp.be_java_hisp_w25_g02.repository.IPostRepository;
import com.bootcamp.be_java_hisp_w25_g02.repository.IUserRepository;
import com.bootcamp.be_java_hisp_w25_g02.repository.PostRepositoryImpl;
import com.bootcamp.be_java_hisp_w25_g02.service.IUserService;
import com.bootcamp.be_java_hisp_w25_g02.util.TestUtilGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.StandardCharsets;
import java.util.List;

@AutoConfigureMockMvc
@SpringBootTest
public class ProductControllerIntegrationTest {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    IPostRepository postRepository;
    @Autowired
    IUserRepository userRepository;
    @Test
    @DisplayName("IntegrationTest - order asc")
    public void getFollowedPostsTestOk() throws Exception {
        //arrange
        Integer userSellerId = userRepository.saveUser(TestUtilGenerator.getUserToFollow());
        User userSeller = TestUtilGenerator.getUserToFollow();
        userSeller.setUserId(userSellerId);
        User userNoSeller = TestUtilGenerator.getUserWithoutFollowed();
        userNoSeller.setFollowing(List.of(userSellerId));
        userRepository.saveUser(userNoSeller);
        List<Post> posts = TestUtilGenerator.getPostsOfUserDisordered(userSellerId);
        postRepository.savePost(posts.get(0));
        postRepository.savePost(posts.get(1));
        postRepository.savePost(posts.get(2));
        FollowingPostDTO expectedResponse = new FollowingPostDTO(
                userNoSeller.getUserId(),
                TestUtilGenerator.getPostsDTOOrderByDateAsc(userSellerId)
        );
        ObjectWriter writer = new ObjectMapper().registerModule(new JavaTimeModule()).configure(SerializationFeature.WRAP_ROOT_VALUE, false).writer();
        String expectedResponseString = writer.writeValueAsString(expectedResponse);
        //act
        MvcResult mvcResult = mockMvc
                .perform(get("/products/followed/{userId}/list", userNoSeller.getUserId())
                        .param("order", "date_asc"))
                .andDo(print())
                .andExpect(content().contentType("application/json"))
                .andExpect(status().isOk())
                .andReturn();
        //assert
        assertEquals(expectedResponseString, mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8));
    }

}
