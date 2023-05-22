package com.example.projetintegr.service;

import com.example.projetintegr.dao.ClubRepository;
import com.example.projetintegr.dao.UserRepository;
import com.example.projetintegr.entities.Club;
import com.example.projetintegr.entities.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserServiceImpl implements UserService{
    @Autowired
    UserRepository userRepo;
    @Override
    public Users saveUser(Users u) {

        return userRepo.save(u);
    }
    @Override
    public List<Users> getAllUser() {

        return userRepo.findAll();
    }

}
