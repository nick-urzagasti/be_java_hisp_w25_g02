package com.bootcamp.be_java_hisp_w25_g02.util;

import com.bootcamp.be_java_hisp_w25_g02.dto.response.UserDTO;
import com.bootcamp.be_java_hisp_w25_g02.dto.response.UserFollowingDTO;
import com.bootcamp.be_java_hisp_w25_g02.entity.User;

import java.util.ArrayList;
import java.util.List;

public class TestUtilGenerator {
    public static UserFollowingDTO getCorrectAscUserFollowingDTO() {
        return new UserFollowingDTO(1, "Javier",
                List.of(new UserDTO(9, "Malena"), new UserDTO(7, "Maria")));
    }

    public static UserFollowingDTO getCorrectDescUserFollowingDTO() {
        return new UserFollowingDTO(1, "Javier",
                List.of(new UserDTO(7, "Maria"), new UserDTO(9, "Malena")));
    }

    public static User getUserWithFollowingSellers() {
        return new User(1, "Javier", false, new ArrayList<>(List.of(
                7, 9
        )), new ArrayList<>());
    }

    public static User followingUserId7() {
        return new User(7, "Maria", true, new ArrayList<>(), new ArrayList<>());
    }

    public static User followingUserId9() {
        return new User(9, "Malena", true, new ArrayList<>(), new ArrayList<>());
    }
}
