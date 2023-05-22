package com.example.projetintegr.service;

import com.example.projetintegr.entities.UsersCertif;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface UsersCertifService {
    UsersCertif saveUsersCertif(UsersCertif userc);
    UsersCertif updatUsersCertif(UsersCertif userc);

    UsersCertif getUsersCertif(Long id);
    List<UsersCertif> getAllUsersCertif();
    Page<UsersCertif> getAllUsersCertifsParPage(int page, int size);

    UsersCertif getUsersCertifById(Long id);


}
