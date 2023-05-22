package com.example.projetintegr.dao;

import com.example.projetintegr.entities.Certif;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CertifRepository extends JpaRepository<Certif, Long> {
    List<Certif> findByNomCertif(String nom);

}
