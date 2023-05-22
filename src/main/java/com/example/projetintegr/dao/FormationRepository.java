package com.example.projetintegr.dao;

import com.example.projetintegr.entities.Club;
import com.example.projetintegr.entities.Formation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FormationRepository extends JpaRepository<Formation, Long> {
      List<Formation> findByNomFormation(String nom);
}
