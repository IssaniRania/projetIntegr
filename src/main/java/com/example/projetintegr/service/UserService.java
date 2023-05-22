package com.example.projetintegr.service;


import com.example.projetintegr.entities.Users;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface UserService{
    Users saveUser(Users u);
    List<Users> getAllUser();
}
