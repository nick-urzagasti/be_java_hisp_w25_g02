package com.bootcamp.be_java_hisp_w25_g02.controller;

import com.bootcamp.be_java_hisp_w25_g02.dto.request.PostDTO;
import com.bootcamp.be_java_hisp_w25_g02.dto.response.FollowingPostDTO;
import com.bootcamp.be_java_hisp_w25_g02.dto.response.GenericResponseDTO;
import com.bootcamp.be_java_hisp_w25_g02.entity.Post;
import com.bootcamp.be_java_hisp_w25_g02.entity.User;
import com.bootcamp.be_java_hisp_w25_g02.repository.IPostRepository;
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
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
class ProductControllerIntegrationTest {
    private final ObjectWriter writer = new ObjectMapper().registerModule(new JavaTimeModule()).configure(SerializationFeature.WRAP_ROOT_VALUE, false).writer();
    @Autowired
    MockMvc mockMvc;
    @Autowired
    IPostRepository postRepository;
    @Autowired
    IUserRepository userRepository;


    @Test
    @DisplayName("IntegrationTest US-0006- listado de posts order asc")
    void getFollowedPostsTestOk() throws Exception {
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
    @DisplayName("IntegrationTest US-0006- obtener post de los seguidos con id de usuario no positivo en el path")
    void getFollowedPostsTestUserIdNotPositive() throws Exception {
        //arrange
        Integer idNoPositivo = 0;
        Set<GenericResponseDTO> expectedResponse = Set.of(
                new GenericResponseDTO("El Id de usuario debe ser un numero positivo")
        );
        String expectedResponseString = writer.writeValueAsString(expectedResponse);
        //act
        MvcResult actualResponse = mockMvc
                .perform(get("/products/followed/{userId}/list", idNoPositivo)
                        .param("order", "date_asc"))
                .andDo(print())
                .andExpect(content().contentType("application/json"))
                .andExpect(status().isBadRequest())
                .andReturn();
        //assert
        assertEquals(expectedResponseString, actualResponse.getResponse().getContentAsString(StandardCharsets.UTF_8));
    }

    @Test
    @DisplayName("IntegrationTest US-0006- listado de posts order desc")
    void getFollowedPostsDescTestOk() throws Exception {
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
    @DisplayName("IntegrationTest US-0006- no hay post de las ultimas dos semanas")
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
    @DisplayName("IntegrationTest US-0006- usuario no existe")
    void getFollowedPostsUserDoesntExists() throws Exception {
        //arrange
        Integer userIdNonExistent = 120;
        while (userRepository.findById(userIdNonExistent).isPresent()){
            userIdNonExistent++;
        }
        GenericResponseDTO expectedResponse = new GenericResponseDTO("El usuario no existe");
        String expectedResponseString = writer.writeValueAsString(expectedResponse);
        //act
        MvcResult actualResponse = mockMvc
                .perform(get("/products/followed/{userId}/list", userIdNonExistent)
                        .param("order", "date_desc"))
                .andDo(print())
                .andExpect(content().contentType("application/json"))
                .andExpect(status().isNotFound())
                .andReturn();
        //assert
        assertEquals(expectedResponseString, actualResponse.getResponse().getContentAsString(StandardCharsets.UTF_8));
    }
    @Test
    @DisplayName("IntegrationTest US-0005- Crear un post OK")
    void createPostOK() throws Exception {
        //arrange
        Integer userSellerId = userRepository.saveUser(TestUtilGenerator.getUserToFollow());
        PostDTO postToBeCreated = TestUtilGenerator.getPostWithUserID(userSellerId);
        //act
        mockMvc.perform(post("/products/post")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(writer.writeValueAsString(postToBeCreated)))
                .andDo(print())
                .andExpect(content().contentType("application/json"))
                .andExpect(status().isOk())
                .andReturn();

    }
    @Test
    @DisplayName("IntegrationTest US-0005- Crear un post por primera vez (se vuelve vendedor) OK")
    void createPostFirstTimeOK() throws Exception {
        //arrange
        Integer userSellerId = userRepository.saveUser(TestUtilGenerator.getUserWithoutFollowed());
        PostDTO postToBeCreated = TestUtilGenerator.getPostWithUserID(userSellerId, 124151);


        //act
         mockMvc.perform(post("/products/post")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(writer.writeValueAsString(postToBeCreated)))
                .andDo(print())
                .andExpect(content().contentType("application/json"))
                .andExpect(status().isOk())
                .andReturn();

    }

    @Test
    @DisplayName("IntegrationTest US-0005- usuario no existe al crear post")
    void createPostUserDoesntExistsTest() throws Exception {
        //arrange
        Integer userNoExistentId = 120;
        while (userRepository.findById(userNoExistentId).isPresent()) {
            userNoExistentId++;
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
    @DisplayName("IntegrationTest US-0005- crearPost con userId menor a 1")
    void createPostWithUserId0() throws Exception {
        Integer userId0 = 0;
        PostDTO postToBeCreated = TestUtilGenerator.getPostWithUserID(userId0);
        GenericResponseDTO expectedResponse = new GenericResponseDTO("El id de usuario debe ser mayor a 0");
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
    @DisplayName("IntegrationTest US-0005- crear post sin post (null)")
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