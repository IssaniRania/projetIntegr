package com.example.projetintegr.dao;

import com.example.projetintegr.entities.Club;
import com.example.projetintegr.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Users, Long> {


}
