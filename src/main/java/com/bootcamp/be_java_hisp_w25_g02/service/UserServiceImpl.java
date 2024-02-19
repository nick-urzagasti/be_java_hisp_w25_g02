package com.bootcamp.be_java_hisp_w25_g02.service;

import com.bootcamp.be_java_hisp_w25_g02.dto.response.FollowerListDTO;
import com.bootcamp.be_java_hisp_w25_g02.dto.response.UserDTO;
import com.bootcamp.be_java_hisp_w25_g02.dto.response.UserFollowingDTO;
import com.bootcamp.be_java_hisp_w25_g02.entity.User;
import com.bootcamp.be_java_hisp_w25_g02.exception.BadRequestException;
import com.bootcamp.be_java_hisp_w25_g02.exception.NotFoundException;
import com.bootcamp.be_java_hisp_w25_g02.repository.IUserRepository;
import com.bootcamp.be_java_hisp_w25_g02.repository.UserRepositoryImpl;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService{

    private IUserRepository userRepository;
    public UserServiceImpl(UserRepositoryImpl userRepository){
        this.userRepository = userRepository;
    }
    @Override
    public List<Integer> getfollowedUsersId(Integer userId) {
        Optional<User> user = this.userRepository.findById(userId);
        if (user.isEmpty()) {
            throw new NotFoundException("El usuario no existe");
        }
        return user.get().getFollowing();
    }

    @Override
    public UserFollowingDTO getFollowedSellers(Integer userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent() && !user.get().getSeller()) {
            List<UserDTO> followingUserIdList = user.get().getFollowing().stream()
                    .map(userRepository::findById)
                    .map(user1 -> new UserDTO(user1.get().getUser_id(), user1.get().getUser_name())).toList();
            return new UserFollowingDTO(user.get().getUser_id(), user.get().getUser_name(), followingUserIdList);
        } else {
            throw new NotFoundException("El usuario solicitado no fue encontrado.");
        }
    }

    @Override
    public FollowerListDTO getFollowersList(Integer userId, String order) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isEmpty()) throw new NotFoundException("No hay usuario asociado a esa ID");
        if (!user.get().getSeller()) throw new BadRequestException("Este usuario no es vendedor, no puede poseer seguidores.");
        List<Integer> followersIdList = user.get().getFollowedBy();
        if (followersIdList.isEmpty()) throw new NotFoundException("El usuario no posee seguidores");
        List<UserDTO> followersList = followersIdList.stream().map(userRepository::findById)
                .map(userA -> new UserDTO(userA.get().getUser_id(), userA.get().getUser_name())).toList();
        // Aquí lógica de ordenamiento si hay orden
        if (order != null && order.equalsIgnoreCase("name_asc")){
            followersList = followersList.stream().sorted(Comparator.comparing(UserDTO::user_name)).toList();
        }
        if (order != null && order.equalsIgnoreCase("name_desc")){
            followersList = followersList.stream().sorted(Comparator.comparing(UserDTO::user_name).reversed()).toList();
        }
        FollowerListDTO ansDTO = new FollowerListDTO(userId, user.get().getUser_name(), followersList);
        return ansDTO;
    }

    public UserDTO userToUserDto(User user) {
        return new UserDTO(user.getUser_id(), user.getUser_name());
    }
}
