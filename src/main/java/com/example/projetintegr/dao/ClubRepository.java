package com.example.projetintegr.dao;

import com.example.projetintegr.entities.Club;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClubRepository extends JpaRepository<Club, Long> {
    //Optional<Club> findById(Long id);
}
