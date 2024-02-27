package com.bootcamp.be_java_hisp_w25_g02.controller;

import com.bootcamp.be_java_hisp_w25_g02.service.IUserService;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class UserControllerTest {
    @Mock
    IUserService userService;
    @Mock
    UserController userController;


}