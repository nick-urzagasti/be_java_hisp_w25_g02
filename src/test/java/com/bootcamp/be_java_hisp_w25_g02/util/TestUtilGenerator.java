package com.bootcamp.be_java_hisp_w25_g02.util;

import com.bootcamp.be_java_hisp_w25_g02.dto.request.PostDTO;
import com.bootcamp.be_java_hisp_w25_g02.dto.response.FollowingPostDTO;
import com.bootcamp.be_java_hisp_w25_g02.dto.response.ProductDTO;
import com.bootcamp.be_java_hisp_w25_g02.entity.Post;
import com.bootcamp.be_java_hisp_w25_g02.entity.Product;
import com.bootcamp.be_java_hisp_w25_g02.entity.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TestUtilGenerator {

    public static List<Post> getPostsDisordered() {
        return List.of(
                new Post(
                        1,
                        1,
                        LocalDate.now().minusDays(1),
                        new Product(
                                1,
                                "Prod1",
                                "tipo1",
                                "brand1",
                                "color1",
                                "notes"
                        ),
                        12,
                        10.0

                ),
                new Post(
                        3,
                        1,
                        LocalDate.now().minusDays(2),
                        new Product(
                                1,
                                "Prod1",
                                "tipo1",
                                "brand1",
                                "color1",
                                "notes"
                        ),
                        12,
                        10.0

                ),

                new Post(
                        2,
                        1,
                        LocalDate.now(),
                        new Product(
                                2,
                                "Prod2",
                                "tipo2",
                                "brand2",
                                "color2",
                                "notes"
                        ),
                        11,
                        11.0

                )
        );
    }
    public static List<PostDTO> getPostsDTODisordered() {
        return List.of(
                new PostDTO(
                        1,
                        LocalDate.now().minusDays(1),
                        new ProductDTO(
                                1,
                                "Prod1",
                                "tipo1",
                                "brand1",
                                "color1",
                                "notes"
                        ),
                        12,
                        10.0

                ),
                new PostDTO(
                        1,
                        LocalDate.now().minusDays(2),
                        new ProductDTO(
                                1,
                                "Prod1",
                                "tipo1",
                                "brand1",
                                "color1",
                                "notes"
                        ),
                        12,
                        10.0

                ),

                new PostDTO(
                        1,
                        LocalDate.now(),
                        new ProductDTO(
                                2,
                                "Prod2",
                                "tipo2",
                                "brand2",
                                "color2",
                                "notes"
                        ),
                        11,
                        11.0

                )
        );
    }


    public static List<PostDTO> getPostsDTOOrderByDateAsc() {
        return List.of(
                new PostDTO(
                        1,
                        LocalDate.now().minusDays(2),
                        new ProductDTO(
                                1,
                                "Prod1",
                                "tipo1",
                                "brand1",
                                "color1",
                                "notes"
                        ),
                        12,
                        10.0
                ),
                new PostDTO(
                        1,
                        LocalDate.now().minusDays(1),
                        new ProductDTO(
                                1,
                                "Prod1",
                                "tipo1",
                                "brand1",
                                "color1",
                                "notes"
                        ),
                        12,
                        10.0

                ),
                new PostDTO(
                        1,
                        LocalDate.now(),
                        new ProductDTO(
                                2,
                                "Prod2",
                                "tipo2",
                                "brand2",
                                "color2",
                                "notes"
                        ),
                        11,
                        11.0
                )
        );
    }
    public static List<PostDTO> getPostsDTOOrderByDateAsc(Integer userSellerId) {
        return List.of(
                new PostDTO(
                        userSellerId,
                        LocalDate.now().minusDays(2),
                        new ProductDTO(
                                1,
                                "Prod1",
                                "tipo1",
                                "brand1",
                                "color1",
                                "notes"
                        ),
                        12,
                        10.0
                ),
                new PostDTO(
                        userSellerId,
                        LocalDate.now().minusDays(1),
                        new ProductDTO(
                                1,
                                "Prod1",
                                "tipo1",
                                "brand1",
                                "color1",
                                "notes"
                        ),
                        12,
                        10.0

                ),
                new PostDTO(
                        userSellerId,
                        LocalDate.now(),
                        new ProductDTO(
                                2,
                                "Prod2",
                                "tipo2",
                                "brand2",
                                "color2",
                                "notes"
                        ),
                        11,
                        11.0
                )
        );
    }
    public static List<PostDTO> getPostsDTOOrderByDateDesc() {
        return List.of(

                new PostDTO(
                        1,
                        LocalDate.now(),
                        new ProductDTO(
                                2,
                                "Prod2",
                                "tipo2",
                                "brand2",
                                "color2",
                                "notes"
                        ),
                        11,
                        11.0
                ),
                new PostDTO(
                        1,
                        LocalDate.now().minusDays(1),
                        new ProductDTO(
                                1,
                                "Prod1",
                                "tipo1",
                                "brand1",
                                "color1",
                                "notes"
                        ),
                        12,
                        10.0

                ),
                new PostDTO(
                        1,
                        LocalDate.now().minusDays(2),
                        new ProductDTO(
                                1,
                                "Prod1",
                                "tipo1",
                                "brand1",
                                "color1",
                                "notes"
                        ),
                        12,
                        10.0
                )
                );
    }

    public static FollowingPostDTO getFollowingPostOrderAsc() {
        return new FollowingPostDTO(
                3,
                getPostsDTOOrderByDateAsc()
        );
    }
    public static FollowingPostDTO getFollowingPostOrderDesc() {
        return new FollowingPostDTO(
                3,
                getPostsDTOOrderByDateDesc()
        );
    }
    public static FollowingPostDTO getFollowingPostDisordered(){
        return new FollowingPostDTO(
                3,
                getPostsDTODisordered()
        );
    }
    public static List<Post> getPostsOfLimitTwoWeeks(){
        return List.of(
                new Post(
                        1,
                        1,
                        LocalDate.now().minusWeeks(2),
                        new Product(
                                1,
                                "Prod1",
                                "tipo1",
                                "brand1",
                                "color1",
                                "notes"
                        ),
                        12,
                        10.0

                ),
                new Post(
                        3,
                        1,
                        LocalDate.now().minusWeeks(2).plusDays(1),
                        new Product(
                                1,
                                "Prod1",
                                "tipo1",
                                "brand1",
                                "color1",
                                "notes"
                        ),
                        12,
                        10.0

                ),

                new Post(
                        2,
                        1,
                        LocalDate.now().minusWeeks(2).minusDays(1),
                        new Product(
                                2,
                                "Prod2",
                                "tipo2",
                                "brand2",
                                "color2",
                                "notes"
                        ),
                        11,
                        11.0

                )
        );
    }

    public static User getUserWithoutFollowed(){
        return new User(
                0,
                "Juan Seguidor",
                false,
                new ArrayList<>(),
                new ArrayList<>()
        );
    }
    public static User getUserToFollow(){
        return new User(
                0,
                "Juan Vendedor",
                true,
                new ArrayList<>(),
                new ArrayList<>()
        );
    }
    public static List<Post> getPostsOfUserDisordered(Integer userId){
        return List.of(
                new Post(
                        0,
                        userId,
                        LocalDate.now().minusDays(1),
                        new Product(
                                1,
                                "Prod1",
                                "tipo1",
                                "brand1",
                                "color1",
                                "notes"
                        ),
                        12,
                        10.0

                ),
                new Post(
                        0,
                        userId,
                        LocalDate.now().minusDays(2),
                        new Product(
                                1,
                                "Prod1",
                                "tipo1",
                                "brand1",
                                "color1",
                                "notes"
                        ),
                        12,
                        10.0

                ),

                new Post(
                        0,
                        userId,
                        LocalDate.now(),
                        new Product(
                                2,
                                "Prod2",
                                "tipo2",
                                "brand2",
                                "color2",
                                "notes"
                        ),
                        11,
                        11.0

                )
        );
    }
}
