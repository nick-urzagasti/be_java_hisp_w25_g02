package com.bootcamp.be_java_hisp_w25_g02.service;

import com.bootcamp.be_java_hisp_w25_g02.dto.response.FollowingPostDTO;
import com.bootcamp.be_java_hisp_w25_g02.dto.response.PostDTO;
import com.bootcamp.be_java_hisp_w25_g02.dto.response.ProductDTO;
import com.bootcamp.be_java_hisp_w25_g02.entity.Post;
import com.bootcamp.be_java_hisp_w25_g02.entity.Product;
import com.bootcamp.be_java_hisp_w25_g02.exception.BadRequestException;
import com.bootcamp.be_java_hisp_w25_g02.exception.NotFoundException;
import com.bootcamp.be_java_hisp_w25_g02.repository.IPostRepository;
import com.bootcamp.be_java_hisp_w25_g02.repository.PostRepositoryImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class PostServiceImpl implements IPostService{
    private IPostRepository postRepository;
    private IUserService userService;
    public PostServiceImpl(PostRepositoryImpl postRepository, UserServiceImpl userService){
        this.postRepository = postRepository;
        this.userService = userService;
    }
    @Override
    public FollowingPostDTO searchPostsOrderedByDate(Long userId, String order) {
        if (!order.equalsIgnoreCase("date_asc") && !order.equalsIgnoreCase("date_desc") ){
            throw new BadRequestException("Parametro order no reconocido. Debe tener el valor date_asc o date_desc");
        }
        List<Long> followedUsers = userService.getfollowedUsersId(userId);
        List<Post> posts = new ArrayList<>();
        for(Long sellerId: followedUsers){
            posts.addAll(this.postRepository.findByUserId(sellerId));

        }

        if (posts.isEmpty()){
            throw new NotFoundException("No hay post de los usuarios seguidos en las ultimas 2 semanas");
        }
        if( order.equalsIgnoreCase("date_asc")){
            posts = posts.stream().sorted(Comparator.comparing(Post::getPostDate)).toList();
        }else {
            posts =posts.stream().sorted(Comparator.comparing(Post::getPostDate).reversed()).toList();
        }
        return new FollowingPostDTO(userId, posts.stream().map(this::mapPostToDTO).toList());
    }
    private PostDTO mapPostToDTO(Post post){
        return new PostDTO(post.getUser_id(), post.getPostDate(), mapToProductDTO(post.getProduct()),post.getCategory(), post.getPrice());

    }
    private ProductDTO mapToProductDTO(Product product){
        return new ProductDTO(product.getProduct_id(), product.getProduct_name(), product.getType(), product.getBrand(), product.getColor(), product.getNotes());
    }

}
