package com.ayush.ecommerce.service;

import com.ayush.ecommerce.entity.User;
import com.ayush.ecommerce.enums.Role;
import com.ayush.ecommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public  class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    public User registerUser(User user){
        if(userRepository.findByEmail(user.getEmail()).isPresent()){
            throw new RuntimeException("email already exists");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(Role.CUSTOMER);
        return userRepository.save(user);

    }
    public User validateUser(String email,String password){
        User user= userRepository.findByEmail(email).orElseThrow(()->new RuntimeException("user not found"));

        if(!passwordEncoder.matches(password,user.getPassword())){
            throw new RuntimeException("wrong password");
        }else{
            return user;
        }


    }


}
