package com.bootcamp.be_java_hisp_w25_g02.service;

import com.bootcamp.be_java_hisp_w25_g02.repository.IUserRepository;
import com.bootcamp.be_java_hisp_w25_g02.dto.response.UserDTO;
import com.bootcamp.be_java_hisp_w25_g02.entity.User;
import com.bootcamp.be_java_hisp_w25_g02.exception.BadRequestException;
import com.bootcamp.be_java_hisp_w25_g02.repository.IUserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    IUserRepository iUserRepository;
    @InjectMocks
    UserServiceImpl userServiceImpl;

    @Test
    @DisplayName("T0004 - When calling getFollowersList(), without an 'order' param, the list is returned without any order.")
    public void getFollowersListTestNoOrderParam(){
        // Arr
        String order = null;

        List<Integer> list1 = List.of(1, 2, 3, 4);
        List<Integer> list2 = List.of(6, 7, 8, 9, 10);
        User user1 = new User(5, "Matias Del Salvador", true, list1, list2);

        List<Integer> list3 = List.of(1, 2, 3, 4);
        List<Integer> list4 = List.of(5, 7, 8, 9, 10);
        User user2 = new User(6, "Romina Fuentes", true, list3, list4);

        List<Integer> list5 = List.of(1, 2, 3, 4);
        List<Integer> list6 = List.of(5, 6, 8, 9, 10);
        User user3 = new User(7, "Abel Gomez", true, list5, list6);

        List<Integer> list8 = List.of(5, 6, 7);
        List<Integer> list9 = List.of(5, 6, 7);
        User user4 = new User(12, "Jorge Alba", true, list8, list9);

        // List<UserDTO> userOptional = List.of(user1, user2, user3);

        // Act

        when(iUserRepository.findById(anyInt())).thenReturn(Optional.of(user4));
        when(iUserRepository.findById(anyInt())).thenReturn(Optional.of(user1));
        when(iUserRepository.findById(anyInt())).thenReturn(Optional.of(user2));
        when(iUserRepository.findById(anyInt())).thenReturn(Optional.of(user3));


        // Assert
        // Assertions.assertEquals();

    }

    @Test
    @DisplayName("T0003 - When calling getFollowersList(), an exception is thrown if 'order' has an incorrect value")
    public void getFollowersListTestIncorrectOrderParam(){
        // Arr

        String incorrectOrderString = "algoIncorrecto";
        /*
        List<Integer> list1 = List.of(1, 2, 3, 4);
        List<Integer> list2 = List.of(6, 7, 8, 9, 10);

        User user = new User(5, "Matias Del Salvador", true, list1, list2);

        Optional<User> userOptional = Optional.of(user);
        */

        // Act and Assert

        Assertions.assertThrows(BadRequestException.class,
                ()-> userServiceImpl.getFollowersList(1, incorrectOrderString));
    }

}