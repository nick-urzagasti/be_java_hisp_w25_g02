package com.bootcamp.be_java_hisp_w25_g02.repository;

import com.bootcamp.be_java_hisp_w25_g02.entity.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepositoryImpl {
    List<User> users;
    public UserRepositoryImpl(){
        loadUsers();
    }
    private void loadUsers(){
        users = new ArrayList<>(List.of(
           new User(1L, "Javier", false, new ArrayList<>()),
                new User(1L, "NU", false, new ArrayList<>()),
                new User(2L, "NA", false, new ArrayList<>()),
                new User(3L, "Martin", false, new ArrayList<>()),
                new User(4L, "Jose234", false, new ArrayList<>()),
                new User(5L, "Juan", true, new ArrayList<>()),
                new User(6L, "Pedro", true, new ArrayList<>()),
                new User(7L, "Maria", false, new ArrayList<>()),
                new User(8L, "Marcos", false, new ArrayList<>()),
                new User(9L, "Malena", true, new ArrayList<>()),
                new User(10L, "JoseMaria", true, new ArrayList<>()),
        new User(11L, "MariaJose", false, new ArrayList<>(List.of(
                new User(1L, "NU", false, new ArrayList<>()),
                new User(4L, "Jose234", false, new ArrayList<>()),
                new User(7L, "Maria", false, new ArrayList<>())
        ))),
        new User(11L, "Jesus", true, new ArrayList<>(List.of(
                new User(1L, "NU", false, new ArrayList<>()),
                new User(4L, "Jose234", false, new ArrayList<>())
        )))

        )
        );
    }

}
