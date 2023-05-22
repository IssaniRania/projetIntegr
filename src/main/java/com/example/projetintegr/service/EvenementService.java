package com.example.projetintegr.service;

import com.example.projetintegr.entities.Certif;
import com.example.projetintegr.entities.Evenement;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EvenementService {
    Evenement saveEvenement(Evenement event);
    Evenement updateEvenement(Evenement event);
    void deleteEvenement(Evenement event);
    void deleteEvenementById(Long id);
    Evenement getEvenement(Long id);
    List<Evenement> getAllEvenement();
    Page<Evenement> getAllEvenementsParPage(int page, int size);

    Evenement getEvenementById(Long id);
    List<Evenement> findByNomEvenement(String nom);

}
