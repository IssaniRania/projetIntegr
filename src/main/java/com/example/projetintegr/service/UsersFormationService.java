package com.example.projetintegr.service;


import com.example.projetintegr.entities.UsersFormation;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UsersFormationService {
    UsersFormation saveUsersFormation(UsersFormation cer);
    UsersFormation updateUsersFormation(UsersFormation userc);


    UsersFormation getUsersFormation(Long id);
    List<UsersFormation> getAllUsersFormation();
    Page<UsersFormation> getAllUsersFormationsParPage(int page, int size);

    UsersFormation getUsersFormationById(Long id);
}
