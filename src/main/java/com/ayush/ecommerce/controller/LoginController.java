package com.ayush.ecommerce.controller;

import com.ayush.ecommerce.dto.LoginResponseDTO;
import com.ayush.ecommerce.dto.UserLoginDTO;
import com.ayush.ecommerce.entity.User;
import com.ayush.ecommerce.jwtsecurity.JwtUtil;
import com.ayush.ecommerce.service.UserService;
import com.ayush.ecommerce.service.UserServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class LoginController {
    @Autowired
    private UserService userService;
    @Autowired
    private JwtUtil jwtUtil;
@PostMapping("/login")
    public ResponseEntity<LoginResponseDTO>login(@Valid@RequestBody UserLoginDTO loginDTO){
    User user =userService.validateUser(loginDTO.getEmail(), loginDTO.getPassword());
    //return new ResponseEntity<>(user, HttpStatus.OK);
    LoginResponseDTO loginResponseDTO=new LoginResponseDTO();

    loginResponseDTO.setToken(jwtUtil.generateToken(user));
    loginResponseDTO.setType("Bearer");
    return new ResponseEntity<>(loginResponseDTO,HttpStatus.OK);
}
}
