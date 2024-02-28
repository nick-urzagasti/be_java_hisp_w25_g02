package com.bootcamp.be_java_hisp_w25_g02.service;

import com.bootcamp.be_java_hisp_w25_g02.dto.response.UserFollowingDTO;
import com.bootcamp.be_java_hisp_w25_g02.entity.User;
import com.bootcamp.be_java_hisp_w25_g02.exception.BadRequestException;
import com.bootcamp.be_java_hisp_w25_g02.repository.IUserRepository;
import com.bootcamp.be_java_hisp_w25_g02.util.TestUtilGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class UserServiceImplTest {

    @Mock
    IUserRepository userRepository;
    @InjectMocks
    UserServiceImpl userService;

    @Test
    @DisplayName("T-0003 - Alphabetical Followed Order Doesn't Exist")
    void followedOrderExistsOK() {
        // Arrange
        Integer id_1 = 1;
        Integer id_7 = 7;
        Integer id_9 = 9;

        String order = null;

        Optional<User> userWithId_1 = Optional.of(TestUtilGenerator.getUserWithFollowingSellers());
        Optional<User> userWithId_7 = Optional.of(TestUtilGenerator.followingUserId7());
        Optional<User> userWithId_9 = Optional.of(TestUtilGenerator.followingUserId9());

        UserFollowingDTO expected = TestUtilGenerator.getCorrectDescUserFollowingDTO();

        when(userRepository.findById(id_1)).thenReturn(userWithId_1);
        when(userRepository.findById(id_7)).thenReturn(userWithId_7);
        when(userRepository.findById(id_9)).thenReturn(userWithId_9);

        // Act
        UserFollowingDTO result = userService.getFollowedSellers(id_1, order);

        // Assert
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("T-0003 - Alphabetical Followed Order Bad Request")
    void followedOrderExistsBadRequest() {
        // Arrange
        Integer id_1 = 1;
        Integer id_7 = 7;
        Integer id_9 = 9;

        String order = "invalid";

        Optional<User> userWithId_1 = Optional.of(TestUtilGenerator.getUserWithFollowingSellers());
        Optional<User> userWithId_7 = Optional.of(TestUtilGenerator.followingUserId7());
        Optional<User> userWithId_9 = Optional.of(TestUtilGenerator.followingUserId9());

        when(userRepository.findById(id_1)).thenReturn(userWithId_1);
        when(userRepository.findById(id_7)).thenReturn(userWithId_7);
        when(userRepository.findById(id_9)).thenReturn(userWithId_9);

        // Act + Assert
        assertThrows(BadRequestException.class, () -> {
            userService.getFollowedSellers(1, order);
        });
    }

    @Test
    @DisplayName("T-0004 - Correct Asc. Alphabetical Followed Order")
    void followedAscOrderOK() {
        // Arrange
        Integer id_1 = 1;
        Integer id_7 = 7;
        Integer id_9 = 9;

        String order = "name_asc";

        Optional<User> userWithId_1 = Optional.of(TestUtilGenerator.getUserWithFollowingSellers());
        Optional<User> userWithId_7 = Optional.of(TestUtilGenerator.followingUserId7());
        Optional<User> userWithId_9 = Optional.of(TestUtilGenerator.followingUserId9());

        UserFollowingDTO expected = TestUtilGenerator.getCorrectAscUserFollowingDTO();

        when(userRepository.findById(id_1)).thenReturn(userWithId_1);
        when(userRepository.findById(id_7)).thenReturn(userWithId_7);
        when(userRepository.findById(id_9)).thenReturn(userWithId_9);

        // Act
        UserFollowingDTO result = userService.getFollowedSellers(id_1, order);

        // Assert
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("T-0004 - Correct Desc. Alphabetical Followed Order")
    void followedDescOrderOK() {
        // Arrange
        Integer id_1 = 1;
        Integer id_7 = 7;
        Integer id_9 = 9;

        String order = "name_desc";

        Optional<User> userWithId_1 = Optional.of(TestUtilGenerator.getUserWithFollowingSellers());
        Optional<User> userWithId_7 = Optional.of(TestUtilGenerator.followingUserId7());
        Optional<User> userWithId_9 = Optional.of(TestUtilGenerator.followingUserId9());

        UserFollowingDTO expected = TestUtilGenerator.getCorrectDescUserFollowingDTO();

        when(userRepository.findById(id_1)).thenReturn(userWithId_1);
        when(userRepository.findById(id_7)).thenReturn(userWithId_7);
        when(userRepository.findById(id_9)).thenReturn(userWithId_9);

        // Act
        UserFollowingDTO result = userService.getFollowedSellers(id_1, order);

        // Assert
        assertEquals(expected, result);
    }
}