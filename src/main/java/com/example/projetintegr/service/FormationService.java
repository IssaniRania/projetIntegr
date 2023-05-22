package com.example.projetintegr.service;

import com.example.projetintegr.entities.Certif;
import com.example.projetintegr.entities.Club;
import com.example.projetintegr.entities.Formation;
import org.springframework.data.domain.Page;

import java.util.List;

public interface FormationService {
    Formation saveFormation(Formation f);
    Formation updateFormation(Formation f);
    void deleteFormation(Formation f);
    void deleteFormationById(Long id);
    Formation getFormation(Long id);
    List<Formation> getAllFormation();
    Page<Formation> getAllFormationsParPage(int page, int size);
    Formation getFormationById(Long id);
    List<Formation> findByNomFormation(String nom);




}
