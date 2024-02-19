package com.bootcamp.be_java_hisp_w25_g02.repository;

import com.bootcamp.be_java_hisp_w25_g02.entity.Post;
import com.bootcamp.be_java_hisp_w25_g02.entity.Product;
import com.bootcamp.be_java_hisp_w25_g02.entity.User;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class PostRepositoryImpl implements IPostRepository
{
    List<Post> postList = new ArrayList<>(List.of(
            new Post(0L, 1L, LocalDate.of(2000, 01, 01),
                    new Product(0, "Pelopincho", "Piletas", "XXX", "Azul", "2000 litros"), 2, 52000.0),
            new Post(1L,
                   2L, LocalDate.of(2005, 02, 10),
                    new Product(0, "Lentes de sol", "Lentes", "Ray Ban", "Gris", "Lorem ipsum"), 3, 13500.0),
            new Post(2L, 4L, LocalDate.of(2005, 02, 10),
                    new Product(0, "Mouse gamer", "Informática", "Logitech", "Gris", "Lorem ipsum"), 4, 7500.0)
    ));

    @Override
    public Optional<Post> findById(long id) {
        return this.postList.stream().filter(post -> post.getPost_id() == id).findFirst();
    }

    @Override
    public List<Post> findAll() {
        return this.postList;
    }

    @Override
    public Optional<Product> findProductById(long id) {
        return this.postList.stream().filter(post -> post.getProduct().getProduct_id() == id).map(Post::getProduct).findFirst();
    }

    @Override
    public long savePost(Post post) {
        post.setPost_id((long) postList.size());
        postList.add(post);
        return post.getPost_id();
    }
}
