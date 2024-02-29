package com.bootcamp.be_java_hisp_w25_g02.controller;

import com.bootcamp.be_java_hisp_w25_g02.dto.response.FollowerListDTO;
import com.bootcamp.be_java_hisp_w25_g02.dto.response.UserDTO;
import com.bootcamp.be_java_hisp_w25_g02.entity.User;
import com.bootcamp.be_java_hisp_w25_g02.repository.IUserRepository;
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
import org.springframework.context.annotation.Lazy;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
class UserControllerIntegrationTest {
    private final ObjectWriter writer = new ObjectMapper().registerModule(new JavaTimeModule()).configure(SerializationFeature.WRAP_ROOT_VALUE, false).writer();
    @Autowired
    MockMvc mockMvc;
    @Autowired
    @Lazy
    IUserRepository userRepository;


    @Test
    @DisplayName("IntegrationTest US-0001- Seguir a un vendedor OK")
    void FollowUserOk() throws Exception {
        //Arrange
        Integer userNoSeller = userRepository.saveUser(TestUtilGenerator.getUserWithoutFollowed());
        Integer userSeller = userRepository.saveUser(TestUtilGenerator.getUserToFollow());
        System.out.println(userSeller);
        System.out.println(userNoSeller);
        //Act
        mockMvc.perform(post("/users/{userId}/follow/{userIdToFollow}", userNoSeller, userSeller))
                .andDo(print())
                .andExpect(status().isOk());

    }
    @Test
    @DisplayName("IntegrationTest US-0004- obtener lista de usuarios que siguen a un vendedor Ok")
    void getFollowersList() throws Exception {
        //arrange
        User userFollower = TestUtilGenerator.getUserWithoutFollowed();
        Integer userFollowerId = userRepository.saveUser(userFollower);
        User userFollower2 = TestUtilGenerator.getUserWithoutFollowed();
        userFollower2.setUserName("Andres");
        Integer userFollowerId2 = userRepository.saveUser(userFollower2);
        User userFollowed = TestUtilGenerator.getUserSeller();
        userFollowed.getFollowedBy().add(userFollowerId);
        userFollowed.getFollowedBy().add(userFollowerId2);
        Integer userFollowedId = userRepository.saveUser(userFollowed);
        String order = "name_asc";
        List<UserDTO> following = List.of(
                new UserDTO(userFollowerId2, userFollower2.getUserName()),
                new UserDTO(userFollowerId, userFollower.getUserName())
        );
        FollowerListDTO expectedResponse = new FollowerListDTO(userFollowedId, userFollowed.getUserName(), following);
        String expectedResponseString = writer.writeValueAsString(expectedResponse);
        //act
        MvcResult actualResponse = mockMvc.perform(
                        get("/user/{userId}/followers", userFollowedId)
                                .param("order", order))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        //assert
        assertEquals(expectedResponseString, actualResponse.getResponse().getContentAsString(StandardCharsets.UTF_8));
    }
    @Test
    @DisplayName("IntegrationTest US-0007 - dejar de seguir un vendedor OK")
    void unFollowUserOk() throws Exception {
        //Arrange
        Integer userSellerId = userRepository.saveUser(TestUtilGenerator.getUserToFollow());
        User userNoSeller = TestUtilGenerator.getUserWithoutFollowed();
        userNoSeller.setFollowing(List.of(userSellerId));
        Integer userNoSellerId = userRepository.saveUser(TestUtilGenerator.getUserWithoutFollowed());
        System.out.println(userNoSellerId);
        System.out.println(userSellerId);
        //Act
        mockMvc.perform(post("/users/{userId}/unfollow/{userIdToFollow}", userNoSellerId, userSellerId))
                .andDo(print())
                .andExpect(status().isOk());

    }


}
