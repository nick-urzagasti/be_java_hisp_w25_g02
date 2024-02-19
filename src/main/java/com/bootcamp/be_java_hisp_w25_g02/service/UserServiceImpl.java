package com.bootcamp.be_java_hisp_w25_g02.service;

import com.bootcamp.be_java_hisp_w25_g02.entity.User;
import com.bootcamp.be_java_hisp_w25_g02.repository.IUserRepository;
import com.bootcamp.be_java_hisp_w25_g02.repository.UserRepositoryImpl;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService{
    IUserRepository userRepository;
    public UserServiceImpl(UserRepositoryImpl userRepository){
        this.userRepository = userRepository;
    }
    @Override
    public boolean existUser(long id) {
        Optional<User> user= userRepository.findById(id);
        return user.isPresent();
    }

    @Override
    public boolean esVendedor(long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent() && user.get().getSeller()){
            return true;
        }else {
            return false;
        }
    }
}
