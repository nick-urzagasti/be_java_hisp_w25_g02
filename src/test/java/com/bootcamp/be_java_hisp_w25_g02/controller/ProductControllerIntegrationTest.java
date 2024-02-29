package com.bootcamp.be_java_hisp_w25_g02.controller;

import com.bootcamp.be_java_hisp_w25_g02.dto.request.PostDTO;
import com.bootcamp.be_java_hisp_w25_g02.dto.response.FollowingPostDTO;
import com.bootcamp.be_java_hisp_w25_g02.dto.response.GenericResponseDTO;
import com.bootcamp.be_java_hisp_w25_g02.entity.Post;
import com.bootcamp.be_java_hisp_w25_g02.entity.User;
import com.bootcamp.be_java_hisp_w25_g02.repository.IPostRepository;
import com.bootcamp.be_java_hisp_w25_g02.repository.IUserRepository;
import com.bootcamp.be_java_hisp_w25_g02.util.TestUtilGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
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
public class ProductControllerIntegrationTest {
    private final ObjectWriter writer = new ObjectMapper().registerModule(new JavaTimeModule()).configure(SerializationFeature.WRAP_ROOT_VALUE, false).writer();
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
    @Test
    @DisplayName("IntegrationTest - order desc")
    public void getFollowedPostsDescTestOk() throws Exception {
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
                TestUtilGenerator.getPostsDTOOrderByDateDesc(userSellerId)
        );
        String expectedResponseString = writer.writeValueAsString(expectedResponse);
        //act
        MvcResult mvcResult = mockMvc
                .perform(get("/products/followed/{userId}/list", userNoSeller.getUserId())
                        .param("order", "date_desc"))
                .andDo(print())
                .andExpect(content().contentType("application/json"))
                .andExpect(status().isOk())
                .andReturn();
        //assert
        assertEquals(expectedResponseString, mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8));
    }
    @Test
    @DisplayName("Integration test - no hay post de las ultimas dos semanas")
    void getFollowedPostNoContentTest() throws Exception {
        //arrange
        Integer userSellerId = userRepository.saveUser(TestUtilGenerator.getUserToFollow());
        User userSellerWithoutPosts = TestUtilGenerator.getUserToFollow();
        userSellerWithoutPosts.setUserId(userSellerId);

        User userNoSeller = TestUtilGenerator.getUserWithoutFollowed();
        userNoSeller.setFollowing(List.of(userSellerId));
        userRepository.saveUser(userNoSeller);
        GenericResponseDTO expectedResponse = new GenericResponseDTO("No hay post de los usuarios seguidos en las ultimas 2 semanas");
        String expectedResponseString = writer.writeValueAsString(expectedResponse);
        //act
        MvcResult actualResponse = mockMvc
                .perform(get("/products/followed/{userId}/list", userNoSeller.getUserId())
                        .param("order", "date_desc"))
                .andDo(print())
                .andExpect(content().contentType("application/json"))
                .andExpect(status().isNotFound())
                .andReturn();
        //assert
        assertEquals(expectedResponseString, actualResponse.getResponse().getContentAsString(StandardCharsets.UTF_8));
    }
    @Test
    @DisplayName("Integration test - Crear un post OK")
    void createPostOK() throws Exception {
        //arrange
        Integer userSellerId = userRepository.saveUser(TestUtilGenerator.getUserToFollow());
        PostDTO postToBeCreated = TestUtilGenerator.getPostWithUserID(userSellerId);
        //act
        MvcResult result = mockMvc.perform(post("/products/post")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(writer.writeValueAsString(postToBeCreated)))
                .andDo(print())
                .andExpect(content().contentType("application/json"))
                .andExpect(status().isOk())
                .andReturn();

    }

    @Test
    @DisplayName("IntegrationTest - usuario no existe")
    void createPostUserDoesntExistsTest() throws Exception {
        //arrange
        Integer userNoExistentId = 120;
        while (userRepository.findById(userNoExistentId).isPresent()){
            userNoExistentId ++;
        }
        PostDTO postToBeCreated = TestUtilGenerator.getPostWithUserID(userNoExistentId);
        GenericResponseDTO expectedResponse = new GenericResponseDTO("El usuario no existe");
        String expectedResponseString = writer.writeValueAsString(expectedResponse);
        //act
        MvcResult actualResponse = mockMvc.perform(post("/products/post")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(writer.writeValueAsString(postToBeCreated)))
                .andDo(print())
                .andExpect(content().contentType("application/json"))
                .andExpect(status().isBadRequest())
                .andReturn();
        //assert
        assertEquals(expectedResponseString, actualResponse.getResponse().getContentAsString(StandardCharsets.UTF_8));
    }

    @Test
    @DisplayName("IntegrationTest Us05 crearPost con userId menor a 1")
    void createPostWithUserId0() throws Exception {
        Integer userId0 = 0;
        PostDTO postToBeCreated = TestUtilGenerator.getPostWithUserID(userId0);
        GenericResponseDTO expectedResponse = new GenericResponseDTO("Validation failed for argument [0] in public org.springframework.http.ResponseEntity<?> com.bootcamp.be_java_hisp_w25_g02.controller.ProductController.savePost(com.bootcamp.be_java_hisp_w25_g02.dto.request.PostDTO): [Field error in object 'postDTO' on field 'userId': rejected value [0]; codes [Min.postDTO.userId,Min.userId,Min.java.lang.Integer,Min]; arguments [org.springframework.context.support.DefaultMessageSourceResolvable: codes [postDTO.userId,userId]; arguments []; default message [userId],1]; default message [El id de usuario debe ser mayor a 0]] ");
        String expectedResponseString = writer.writeValueAsString(expectedResponse);
        //act
        MvcResult actualResponse = mockMvc.perform(post("/products/post")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(writer.writeValueAsString(postToBeCreated)))
                .andDo(print())
                .andExpect(content().contentType("application/json"))
                .andExpect(status().isBadRequest())
                .andReturn();
        //assert
        assertEquals(expectedResponseString, actualResponse.getResponse().getContentAsString(StandardCharsets.UTF_8));
    }
    @Test
    @DisplayName("IntegrationTest Us05 crear post sin post")
    void createPostWithoutAPost() throws Exception {
        PostDTO postToBeCreated = null;

        //act
        MvcResult actualResponse = mockMvc.perform(post("/products/post")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(writer.writeValueAsString(postToBeCreated)))
                .andDo(print())
                .andExpect(content().contentType("application/json"))
                .andExpect(status().isBadRequest())
                .andReturn();
          }
}
