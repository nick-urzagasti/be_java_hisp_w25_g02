package com.bootcamp.be_java_hisp_w25_g02.dto.response;

import java.util.List;

public record FollowerListDTO(
        Long user_id,
        String user_name,
        List<UserDTO> followers
) {
}
