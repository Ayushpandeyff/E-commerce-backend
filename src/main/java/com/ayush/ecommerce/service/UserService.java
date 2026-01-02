package com.ayush.ecommerce.service;

import com.ayush.ecommerce.entity.User;

public  interface UserService  {
    User registerUser(User user);
    User validateUser(String email,String Password);
}
