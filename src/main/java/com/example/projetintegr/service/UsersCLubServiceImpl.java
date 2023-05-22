package com.example.projetintegr.service;

import com.example.projetintegr.dao.UsersCLubRepository;

import com.example.projetintegr.entities.UsersCLub;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsersCLubServiceImpl implements UsersCLubService{
    @Autowired
    UsersCLubRepository usersCLubRepository;
    @Override
    public UsersCLub saveUsersCLub(UsersCLub userc) {
        return usersCLubRepository.save(userc);
    }

    @Override
    public UsersCLub updateUsersCLub(UsersCLub userc) {
        return usersCLubRepository.save(userc);
    }





    @Override
    public UsersCLub getUsersCLub(Long id) {
        return  usersCLubRepository.findById(id).get();

    }

    @Override
    public List<UsersCLub> getAllUsersCLub() {
        return usersCLubRepository.findAll();
    }

    @Override
    public Page<UsersCLub> getAllUsersCLubsParPage(int page, int size) {
        return usersCLubRepository.findAll(PageRequest.of(page, size));
    }

    @Override
    public UsersCLub getUsersCLubById(Long id) {
        Optional<UsersCLub> usersCLub = usersCLubRepository.findById(id);//option est une class pour tester club vide ou non
        return usersCLub.orElse(null);//or
         }
}
