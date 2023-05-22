package com.example.projetintegr.dao;

import com.example.projetintegr.entities.Certif;
import com.example.projetintegr.entities.UsersCertif;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UsersCertifRepository extends JpaRepository<UsersCertif, Long> {

}
