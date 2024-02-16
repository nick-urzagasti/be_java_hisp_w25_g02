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
        User usuarioVendedor1 = new User(4L, "Jose234", true, new ArrayList<>(), new ArrayList<>());
        User usuarioVendedor2 = new User(5L, "Juan", true, new ArrayList<>(), new ArrayList<>());
        User usuarioSeguidor1 =   new User(11L, "MariaJose", false, new ArrayList<>(), new ArrayList<>());
        usuarioSeguidor1.getFollowing().add(usuarioVendedor1);
        usuarioSeguidor1.getFollowing().add(usuarioVendedor2);
        usuarioVendedor1.getFollowedBy().add(usuarioSeguidor1);
        usuarioVendedor2.getFollowedBy().add(usuarioSeguidor1);
        users = new ArrayList<>(List.of(
           new User(1L, "Javier", false, new ArrayList<>(), new ArrayList<>()),
                new User(1L, "NU", false, new ArrayList<>(), new ArrayList<>()),
                new User(2L, "NA", false, new ArrayList<>(), new ArrayList<>()),
                new User(3L, "Martin", false, new ArrayList<>(), new ArrayList<>()),
                new User(7L, "Maria", true, new ArrayList<>(), new ArrayList<>()),
                new User(8L, "Marcos", false, new ArrayList<>(), new ArrayList<>()),
                new User(9L, "Malena", true, new ArrayList<>(), new ArrayList<>()),
                new User(10L, "JoseMaria", true, new ArrayList<>(), new ArrayList<>())
      , usuarioVendedor1, usuarioVendedor2, usuarioSeguidor1


        )
        );
    }

}
