package com.bootcamp.be_java_hisp_w25_g02.service;

import com.bootcamp.be_java_hisp_w25_g02.dto.response.FollowerCountDTO;
import com.bootcamp.be_java_hisp_w25_g02.dto.response.UserDTO;
import com.bootcamp.be_java_hisp_w25_g02.entity.User;
import com.bootcamp.be_java_hisp_w25_g02.exception.BadRequestException;
import com.bootcamp.be_java_hisp_w25_g02.repository.IUserRepository;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService{
    private final IUserRepository userRepo;

    public UserServiceImpl(IUserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public FollowerCountDTO getUserTotalFollowers(Integer id) {
        Optional<User> user = userRepo.findById(id);
        if(user.isPresent()){
            return new FollowerCountDTO(user.get().getUser_id(),
                    user.get().getUser_name(),
                    user.get().getFollowing().stream().count());
        } else {
            throw new BadRequestException("No encontrado el user con ese ID");
        }
    }
}
