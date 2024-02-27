package com.bootcamp.be_java_hisp_w25_g02.service;

import com.bootcamp.be_java_hisp_w25_g02.repository.IUserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    IUserRepository userRepository;
    @InjectMocks
    UserServiceImpl userService;

    @Test
    @DisplayName("T-0003 - Alphabetical Followed Order Exists")
    void followedOrderExistsOK() {

    }

    @Test
    @DisplayName("T-0004 - Correct Asc. Alphabetical Followed Order")
    void followedAscOrderOK() {
        // Arrange


        // Act


        // Assert
    }

    @Test
    @DisplayName("T-0004 - Correct Desc. Alphabetical Followed Order")
    void followedDescOrderOK() {
        // Arrange


        // Act


        // Assert
    }
}