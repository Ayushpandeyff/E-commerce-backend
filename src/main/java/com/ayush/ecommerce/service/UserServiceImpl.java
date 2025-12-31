package com.ayush.ecommerce.service;

import com.ayush.ecommerce.entity.User;
import com.ayush.ecommerce.enums.Role;
import com.ayush.ecommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public  class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    public User registerUser(User user){
        if(userRepository.findByEmail(user.getEmail()).isPresent()){
            throw new RuntimeException("email already exists");
        }
        user.setRole(Role.CUSOTMER);
        return userRepository.save(user);
    }


}
