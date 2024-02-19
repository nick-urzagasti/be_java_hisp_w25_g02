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
           new User(1, "Javier", false, new ArrayList<>(List.of(
                   7,9, 10

           )), new ArrayList<>()),
                new User(2, "NA", false, new ArrayList<>(), new ArrayList<>()),
                new User(3, "Martin", false, new ArrayList<>(), new ArrayList<>()),
                new User(7, "Maria", true, new ArrayList<>(),

                        new ArrayList<>(List.of(1)
                        )),
                new User(8, "Marcos", false, new ArrayList<>(), new ArrayList<>(

                )),
                new User(9, "Malena", true, new ArrayList<>(), new ArrayList<>(
                        List.of(1)
                )),
                new User(10, "JoseMaria", true, new ArrayList<>(), new ArrayList<>(
                        List.of(1)
                ))

        ));

        System.out.println(users);
    }

    @Override
    public List<User> getFollowersList(long id) {
        System.out.println("Los User son: " + users);
        Optional<User> myUser = this.users.stream().filter(user -> user.getUser_id() == id).findFirst();
        System.out.println(myUser);
        List<Integer> followersListIds = new ArrayList<>();
        List<User> followersList = new ArrayList<>();
        if (myUser.isPresent()) {
            followersListIds = myUser.get().getFollowedBy();
            System.out.println("Lista de Id seguidores " + followersListIds);
            followersListIds.forEach( searchId -> {
                Optional<User> follower = this.users.stream().filter(u -> u.getUser_id().equals(searchId)).findFirst();
                follower.ifPresent(followersList::add);
            });
        }
        return followersList;
    }

    @Override
    public Optional<User> findById(Integer id) {
        return this.users.stream().filter(user -> user.getUser_id().equals(id)).findFirst();
    }

    @Override
    public List<User> findAll() {
        return this.users;
    }


}
