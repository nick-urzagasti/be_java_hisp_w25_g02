package com.bootcamp.be_java_hisp_w25_g02.service;

import com.bootcamp.be_java_hisp_w25_g02.repository.IUserRepository;
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
}