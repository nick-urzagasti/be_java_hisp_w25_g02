package com.bootcamp.be_java_hisp_w25_g02.dto.response;

import java.util.List;

public record FollowingPostDTO(
    Long user_id,
    List<PostDTO> posts
) {
}
