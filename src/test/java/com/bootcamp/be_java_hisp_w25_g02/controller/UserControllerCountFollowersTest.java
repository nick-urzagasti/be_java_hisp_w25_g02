package com.bootcamp.be_java_hisp_w25_g02.controller;

import com.bootcamp.be_java_hisp_w25_g02.dto.response.FollowerCountDTO;
import com.bootcamp.be_java_hisp_w25_g02.service.IUserService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Objects;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
class UserControllerCountFollowersTest {
    @Mock
    IUserService userService;
    @InjectMocks
    UserController userController;
    @Test
    void getUserTotalFollowers() throws Exception {
        //Arrange - llama elementos a usar
        FollowerCountDTO expected = new FollowerCountDTO(1, "Javier", 2L);
        when(userService.getUserTotalFollowers(1)).thenReturn(expected);
        //Act - ejecuta el método a testear
        ResponseEntity<FollowerCountDTO> current = userController.getUserTotalFollowers(1);
        //Assert - verifica que el resultado sea lo esperado
        assertThat(current.getStatusCode()).isEqualTo(HttpStatus.OK);
        FollowerCountDTO actual = current.getBody();
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @Disabled
    void getUserTotalFollowersCountPath() throws Exception {
        //Arrange - llama elementos a usar
        Long expected = 2L;
        when(userService.getUserTotalFollowers(1).followersCount()).thenReturn(expected);
        //Act - ejecuta el método a testear
        ResponseEntity<FollowerCountDTO> current = userController.getUserTotalFollowers(1);
        //Assert - verifica que el resultado sea lo esperado
        System.out.println(expected);
        System.out.println(current);
        assertThat(expected).isEqualTo(current);
    }
}