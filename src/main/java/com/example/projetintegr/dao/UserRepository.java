package com.example.projetintegr.dao;

import com.example.projetintegr.entities.Club;
import com.example.projetintegr.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users, Long> {
}
