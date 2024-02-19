package com.bootcamp.be_java_hisp_w25_g02.repository;

import com.bootcamp.be_java_hisp_w25_g02.entity.Post;
import com.bootcamp.be_java_hisp_w25_g02.entity.Product;
import com.bootcamp.be_java_hisp_w25_g02.entity.User;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.*;

@Repository
public class PostRepositoryImpl implements IPostRepository
{
    List<Post> postList = new ArrayList<>(List.of(
            new Post(0L, 7L, LocalDate.of(2024, 02, 17),
                    new Product(0, "Pelopincho", "Piletas", "XXX", "Azul", "2000 litros"), 2, 52000.0),
            new Post(1L,
                   7L, LocalDate.of(2024, 02, 19),
                    new Product(0, "Lentes de sol", "Lentes", "Ray Ban", "Gris", "Lorem ipsum"), 3, 13500.0),
            new Post(9L, 9L, LocalDate.of(2024, 02, 18),
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
    public List<Post> findByUserId(Long userId) {
        LocalDate twoWeeksAgo = LocalDate.now().minusWeeks(2);
        return this.postList.stream().filter(post -> post.getPostDate().isAfter(twoWeeksAgo) && Objects.equals(post.getUser_id(), userId)).toList();
    }
}
