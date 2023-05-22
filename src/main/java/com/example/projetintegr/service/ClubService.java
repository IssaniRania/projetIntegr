package com.example.projetintegr.service;

import com.example.projetintegr.entities.Certif;
import com.example.projetintegr.entities.Club;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface ClubService {
    Club saveClub(Club c);
    Club updateClub(Club c);
    void deleteClub(Club c);
    void deleteClubById(Long id);
    Club getClub(Long id);
    List<Club>getAllClub();
    Page<Club> getAllClubsParPage(int page, int size);

    Club getClubById(Long id);
    Club findById(Long Id);
    List<Club> findByNomClub(String nom);


}
