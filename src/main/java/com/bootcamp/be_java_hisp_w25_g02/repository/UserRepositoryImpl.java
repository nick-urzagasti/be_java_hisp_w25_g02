package com.bootcamp.be_java_hisp_w25_g02.repository;

import com.bootcamp.be_java_hisp_w25_g02.entity.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class UserRepositoryImpl implements IUserRepository{
    List<User> users;
    public UserRepositoryImpl(){
        loadUsers();
    }
    private void loadUsers(){
        users = new ArrayList<>(List.of(
           new User(1L, "Javier", false, new ArrayList<>(List.of(
                   7L,9L, 10L

           )), new ArrayList<>()),
                new User(2L, "NA", false, new ArrayList<>(), new ArrayList<>()),
                new User(3L, "Martin", false, new ArrayList<>(), new ArrayList<>()),
                new User(7L, "Maria", true, new ArrayList<>(),

                        new ArrayList<>(List.of(1L)
                        )),
                new User(8L, "Marcos", false, new ArrayList<>(), new ArrayList<>(

                )),
                new User(9L, "Malena", true, new ArrayList<>(), new ArrayList<>(
                        List.of(1L)
                )),
                new User(10L, "JoseMaria", true, new ArrayList<>(), new ArrayList<>(
                        List.of(1L)
                ))

        ));
    }

    @Override
    public Optional<User> findById(long id) {
        return this.users.stream().filter(user -> user.getUser_id() == id).findFirst();
    }

    @Override
    public List<User> findAll() {
        return this.users;
    }
}
