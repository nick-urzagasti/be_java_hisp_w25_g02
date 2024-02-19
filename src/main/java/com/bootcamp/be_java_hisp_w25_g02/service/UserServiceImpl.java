package com.bootcamp.be_java_hisp_w25_g02.service;

import com.bootcamp.be_java_hisp_w25_g02.dto.response.UserDTO;
import com.bootcamp.be_java_hisp_w25_g02.dto.response.UserFollowingDTO;
import com.bootcamp.be_java_hisp_w25_g02.entity.User;
import com.bootcamp.be_java_hisp_w25_g02.exception.NotFoundException;
import com.bootcamp.be_java_hisp_w25_g02.repository.IUserRepository;
import com.bootcamp.be_java_hisp_w25_g02.repository.UserRepositoryImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService{

    private final IUserRepository userRepository;
    public UserServiceImpl(UserRepositoryImpl userRepository){
        this.userRepository = userRepository;
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
}
