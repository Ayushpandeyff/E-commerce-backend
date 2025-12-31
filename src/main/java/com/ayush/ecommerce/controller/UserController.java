package com.ayush.ecommerce.controller;

import com.ayush.ecommerce.dto.UserRegisterDTO;
import com.ayush.ecommerce.entity.User;
import com.ayush.ecommerce.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping("/check")
    public String check(){
        return "check sucesss";
    }
@PostMapping("/register")
    public ResponseEntity<User> registerUser(@Valid @RequestBody UserRegisterDTO dto){
    System.out.println("REGISTER API HIT");
    User user= new User();
    user.setFirstName(dto.getFirstName());
    user.setLastName(dto.getLastName());
    user.setEmail(dto.getEmail());
    user.setPassword(dto.getPassword());
    user.setPhoneNumber(dto.getPhoneNumber());
    User SavedUser= userService.registerUser(user);
    return new ResponseEntity<>(SavedUser, HttpStatus.CREATED);
}
}
