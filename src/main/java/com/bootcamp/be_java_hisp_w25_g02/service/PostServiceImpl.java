package com.bootcamp.be_java_hisp_w25_g02.service;

import com.bootcamp.be_java_hisp_w25_g02.dto.response.GenericResponseDTO;
import com.bootcamp.be_java_hisp_w25_g02.dto.response.PostDTO;
import com.bootcamp.be_java_hisp_w25_g02.dto.response.ProductDTO;
import com.bootcamp.be_java_hisp_w25_g02.entity.Post;
import com.bootcamp.be_java_hisp_w25_g02.entity.Product;
import com.bootcamp.be_java_hisp_w25_g02.entity.User;
import com.bootcamp.be_java_hisp_w25_g02.exception.BadRequestException;
import com.bootcamp.be_java_hisp_w25_g02.repository.IPostRepository;
import com.bootcamp.be_java_hisp_w25_g02.repository.PostRepositoryImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

@Service
public class PostServiceImpl implements IPostService{
    IPostRepository postRepository;
    IUserService userService;
    public PostServiceImpl(PostRepositoryImpl postRepository, UserServiceImpl userService){
        this.postRepository = postRepository;
        this.userService =userService;
    }

    public GenericResponseDTO savePost(PostDTO post){
        if (!userService.existUser(post.user_id())){
            throw new BadRequestException("El Usuario no existe");
        }
        if (!userService.esVendedor(post.user_id())){
            throw new BadRequestException("El usuario no es vendedor");
        }
        if (this.postRepository.findProductById(post.product().product_id()).isPresent()){
            throw new BadRequestException("Ya existe un Producto con ese ID");
        }

        Post postReal = mapDtoToPost(post);
        long id = this.postRepository.savePost(postReal);
        System.out.println(this.postRepository.findAll().toString());
        return new GenericResponseDTO("Post creado con exito con el id: "+ id);
    }
    private Post mapDtoToPost(PostDTO postDTO){
        return new Post(0L, postDTO.user_id(), postDTO.date(), new Product(postDTO.product().product_id(), postDTO.product().product_name(), postDTO.product().type(), postDTO.product().brand(), postDTO.product().color(), postDTO.product().notes()), postDTO.category(), postDTO.price());
    }
}
