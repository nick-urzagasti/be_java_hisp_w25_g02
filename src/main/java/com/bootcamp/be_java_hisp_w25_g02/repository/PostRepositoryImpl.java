package com.bootcamp.be_java_hisp_w25_g02.repository;

import com.bootcamp.be_java_hisp_w25_g02.entity.Post;
import com.bootcamp.be_java_hisp_w25_g02.entity.Product;
import com.bootcamp.be_java_hisp_w25_g02.entity.User;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository 
public class PostRepositoryImpl {
    List<Post> postList = new ArrayList<>(List.of(
            new Post(0L,
                    new User(0L, "Juan", true, List.of(
                            new User(1L, "Pepe", false, null, null)), null), LocalDate.of(2000, 01, 01),
                    new Product(0, "Pelopincho", "Piletas", "XXX", "Azul", "2000 litros"), 2, 52000.0),
            new Post(1L,
                    new User(2L, "John", true, List.of(
                            new User(3L, "Juana", false, null, null)), null), LocalDate.of(2005, 02, 10),
                    new Product(0, "Lentes de sol", "Lentes", "Ray Ban", "Gris", "Lorem ipsum"), 3, 13500.0),
            new Post(2L,
                    new User(4L, "Martin", true, List.of(
                            new User(5L, "Ariel", false, null, null)), null), LocalDate.of(2005, 02, 10),
                    new Product(0, "Mouse gamer", "Informática", "Logitech", "Gris", "Lorem ipsum"), 4, 7500.0)
    ));
}
