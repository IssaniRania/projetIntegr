package com.example.projetintegr.dao;

import com.example.projetintegr.entities.Club;
import com.example.projetintegr.entities.Evenement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ClubRepository extends JpaRepository<Club, Long> {
 /*   Optional<Club> findById(Long id);
    List<Club> findByName(String name);*/
    List<Club> findByNomClub(String nom);

}
