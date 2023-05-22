package com.example.projetintegr.service;

import com.example.projetintegr.entities.UsersCLub;
import com.example.projetintegr.entities.UsersCertif;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface UsersCLubService {
    UsersCLub saveUsersCLub(UsersCLub userc);
    UsersCLub updateUsersCLub(UsersCLub userc);

    UsersCLub getUsersCLub(Long id);
    List<UsersCLub> getAllUsersCLub();
    Page<UsersCLub> getAllUsersCLubsParPage(int page, int size);

    UsersCLub getUsersCLubById(Long id);


}
