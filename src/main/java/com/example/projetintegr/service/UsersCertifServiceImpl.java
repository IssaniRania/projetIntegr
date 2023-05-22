package com.example.projetintegr.service;

import com.example.projetintegr.dao.UsersCertifRepository;
import com.example.projetintegr.entities.UsersCertif;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class UsersCertifServiceImpl implements UsersCertifService {
    @Autowired
     UsersCertifRepository  usersCertifRepository;
    @Override
    public UsersCertif saveUsersCertif(UsersCertif userc) {
        return usersCertifRepository.save(userc);
    }

    @Override
    public UsersCertif updatUsersCertif(UsersCertif userc) {
        return usersCertifRepository.save(userc);
    }

    @Override
    public UsersCertif getUsersCertif(Long id) {
        return usersCertifRepository.findById(id).get();
    }

    @Override
    public List<UsersCertif> getAllUsersCertif() {
        return usersCertifRepository.findAll();
    }

    @Override
    public Page<UsersCertif> getAllUsersCertifsParPage(int page, int size) {
        return usersCertifRepository.findAll(PageRequest.of(page, size));
    }

    @Override
    public UsersCertif getUsersCertifById(Long id) {
        Optional<UsersCertif> usersCertif = usersCertifRepository.findById(id);//option est une class pour tester club vide ou non
        return usersCertif.orElse(null);   }

}
