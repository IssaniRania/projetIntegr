package com.example.projetintegr.dao;

import com.example.projetintegr.entities.Certif;
import com.example.projetintegr.entities.Evenement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EvenementRepository extends JpaRepository<Evenement, Long> {
    List<Evenement> findByNomEvenement(String nom);

}
