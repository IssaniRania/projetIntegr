package com.example.projetintegr.service;


import com.example.projetintegr.dao.UsersFormationRepository;
import com.example.projetintegr.entities.UsersFormation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsersFormationServiceImpl implements UsersFormationService{
    @Autowired
    UsersFormationRepository usersFormationRepository;
    @Override
    public UsersFormation saveUsersFormation(UsersFormation cer) {
        return usersFormationRepository.save(cer);
    }

    @Override
    public UsersFormation updateUsersFormation(UsersFormation userc) {
        return usersFormationRepository.save(userc);
    }


    @Override
    public UsersFormation getUsersFormation(Long id) {
        return  usersFormationRepository.findById(id).get();
    }

    @Override
    public List<UsersFormation> getAllUsersFormation() {
        return usersFormationRepository.findAll();
    }

    @Override
    public Page<UsersFormation> getAllUsersFormationsParPage(int page, int size) {
        return usersFormationRepository.findAll(PageRequest.of(page, size));
    }

    @Override
    public UsersFormation getUsersFormationById(Long id) {
        Optional<UsersFormation> usersFormation = usersFormationRepository.findById(id);//option est une class pour tester club vide ou non
        return usersFormation.orElse(null);//or
    }
}
